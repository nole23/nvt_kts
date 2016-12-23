package com.konstrukcija.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.konstrukcija.model.Kompanija;
import com.konstrukcija.repository.KompanijaRepository;

@Service
public class KompanijaService {

	@Autowired
	KompanijaRepository kompanijaRepository;
	
	public Kompanija save(Kompanija kompanija) {
		return kompanijaRepository.save(kompanija);
	}
	
	public Kompanija findOne(Long id) {
		return kompanijaRepository.findOne(id);
	}
}
