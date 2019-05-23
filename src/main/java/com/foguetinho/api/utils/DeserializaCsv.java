package com.foguetinho.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foguetinho.api.model.RegTelefonico;
import com.foguetinho.api.model.TipoIsolamento;
import com.foguetinho.api.repository.TipoIsolamentoRepository;

@Component
public class DeserializaCsv {
	
	@Autowired
	private TipoIsolamentoRepository tipoIsolamentoRepository;
	
	public RegTelefonico deserialize(BufferedReader br) throws IOException {
		Integer id = Integer.parseInt(br.readLine());
		System.out.println("debug" + id);
		Optional<TipoIsolamento> tipoIsolamento = tipoIsolamentoRepository.findById(id);
			
		//Telefones
		String telefones = br.readLine();
		
		String telefone[] = telefones.split(",");
		
		String telefoneOrigem;
		String telefoneDestino;
		
		if(telefone.length == 2) {
			telefoneOrigem = telefone[0];
			telefoneDestino = telefone[1];
		}else {
			telefoneOrigem = telefone[0].substring(0, 12);
			telefoneDestino = telefone[0].substring(13, 25);
		}
				
		//Registro telefonico
		RegTelefonico regTelefonico = new RegTelefonico();
		regTelefonico.setTelefoneDestino(Long.parseLong(telefoneDestino));
		regTelefonico.setTelefoneOrigem(Long.parseLong(telefoneOrigem));
		regTelefonico.setTipoIsolamento(tipoIsolamento.get());
		
		return regTelefonico;
	}
}
