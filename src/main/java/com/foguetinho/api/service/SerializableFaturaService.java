package com.foguetinho.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.foguetinho.api.model.RegTelefonico;
import com.foguetinho.api.repository.RegTelefonicoRepository;

@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.MANDATORY)
@Service
public class SerializableFaturaService implements IRegistraFaturaService {

	@Autowired
	private RegTelefonicoRepository regTelefonicoRepository;
	
	@Override
	public void save(RegTelefonico regTelefonico) {
		System.out.println(TransactionAspectSupport.currentTransactionStatus().isNewTransaction());
		regTelefonicoRepository.save(regTelefonico);
	}

}
