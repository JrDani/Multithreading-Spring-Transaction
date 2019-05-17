package com.foguetinho.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.foguetinho.api.model.RegTelefonico;
import com.foguetinho.api.repository.RegTelefonicoRepository;

@Transactional
@Service
public class RegistraFaturaService implements IRegistraFaturaService {
	
	@Autowired
	private RegTelefonicoRepository regTelefonicoRepository;

	@Override
	public void save(RegTelefonico regTelefonico) {
		System.out.println(TransactionAspectSupport.currentTransactionStatus());
		System.out.println("cod 2 - Default");
		regTelefonicoRepository.save(regTelefonico);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void saveSerie(RegTelefonico rt) {
		System.out.println(TransactionAspectSupport.currentTransactionStatus());
		System.out.println("cod 1 - serie");
		regTelefonicoRepository.save(rt);
	}
}
