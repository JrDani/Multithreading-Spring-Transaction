package com.foguetinho.api.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.foguetinho.api.model.RegTelefonico;

@Transactional
@Service
public interface IRegistraFaturaService {
		
	public void save(RegTelefonico regTelefonico);

	public void saveSerie(RegTelefonico rt);
}
