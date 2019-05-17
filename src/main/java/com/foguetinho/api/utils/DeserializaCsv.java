package com.foguetinho.api.utils;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.foguetinho.api.model.RegTelefonico;
import com.foguetinho.api.model.TipoIsolamento;

@Component
public class DeserializaCsv {
	
	public static RegTelefonico deserialize(BufferedReader br) throws IOException {
		
		//Isolamento
		TipoIsolamento tipoIsolamento = new TipoIsolamento();
		tipoIsolamento.setId(Integer.parseInt(br.readLine()));
		
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
		regTelefonico.setTipoIsolamento(tipoIsolamento);
		
		return regTelefonico;
	}
}
