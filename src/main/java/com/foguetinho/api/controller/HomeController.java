package com.foguetinho.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.foguetinho.api.utils.storage.StorageFileNotFoundException;
import com.foguetinho.api.utils.storage.StorageService;

@Controller
public class HomeController {
	private final StorageService storageService;
	private List<RegTelefonico> regTelefonico = new ArrayList<>();
	
	/*service para puxar o arquivo do html e jogar no diretorio "upload-dir" e retornar um model
	registro telefonico*/
    @Autowired
    public HomeController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @Autowired
    private IRegistraFaturaService registraFaturaService;
      
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
    public String persist() {
    	
    	for(RegTelefonico rt: regTelefonico) {
    		switch (rt.getTipoIsolamento().getId()) {
			case 1:
				System.out.println("persistindo serializable");						
				registraFaturaService.saveSerie(rt);
				break;
				
			default:
				System.out.println("persistindo default");					
				registraFaturaService.save(rt); 
			}    				
    	}
    	
    	/*
    	ThreadPoolExecutor executor = 
			  (ThreadPoolExecutor) Executors.newFixedThreadPool(regTelefonico.size());
			
    		for(RegTelefonico rt: regTelefonico) {
    			executor.submit(() -> {  
    				
    				Random r = new Random();    				
					long timer = (long)(r.nextDouble()*10000L);	
					System.out.println(rt.getTipoIsolamento().getId()+" Dormindo por " + timer);
					Thread.sleep(timer);
					
					switch (rt.getTipoIsolamento().getId()) {
					case 1:
						System.out.println("serie");						
						registraFaturaService.saveSerie(rt);
						break;
						
					default:
						System.out.println("default");					
						registraFaturaService.save(rt); 
					}    								
    				return null;
    			});				
			}	
    		*/
		return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
