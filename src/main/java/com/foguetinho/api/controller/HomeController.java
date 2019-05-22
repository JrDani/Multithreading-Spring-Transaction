package com.foguetinho.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foguetinho.api.model.RegTelefonico;
import com.foguetinho.api.service.IRegistraFaturaService;
import com.foguetinho.api.service.RegistraFaturaService;
import com.foguetinho.api.service.SerializableFaturaService;
import com.foguetinho.api.utils.storage.StorageFileNotFoundException;
import com.foguetinho.api.utils.storage.StorageService;

@Controller
@EnableTransactionManagement
public class HomeController {
	private final StorageService storageService;
	private final IRegistraFaturaService dRegistraFaturaService;
	private final IRegistraFaturaService sRegistraFaturaService;
	
	private List<RegTelefonico> regTelefonico = new ArrayList<>();
		
    @Autowired
    public HomeController(StorageService storageService, RegistraFaturaService dfs, SerializableFaturaService sfs) {
        this.storageService = storageService;
        this.dRegistraFaturaService = dfs;
        this.sRegistraFaturaService = sfs;
    }
             
    @GetMapping("/")
    public ModelAndView listUploadedFiles(Model model) throws IOException {
    	ModelAndView modelAndView = new ModelAndView("home");
    	
    	modelAndView.addObject("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(HomeController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return modelAndView;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

    	regTelefonico.add(storageService.store(file));
        
        redirectAttributes.addFlashAttribute("message",
                "Upload feito com sucesso " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
    
    @GetMapping("/subir-pro-banco")    
    @Transactional
    public String persist() {
    	System.out.println("Sessão do usuário");
    	for(RegTelefonico rt: regTelefonico) {
    		switch (rt.getTipoIsolamento().getId()) {
			case 1:
				System.out.println("persistindo serializable");						
				sRegistraFaturaService.save(rt);
				break;
				
			default:
				System.out.println("persistindo default");					
				dRegistraFaturaService.save(rt); 
			}    				
    	}
    	
		return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
