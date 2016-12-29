package com.konstrukcija.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.konstrukcija.model.Oglas;
import com.konstrukcija.repository.OglasRepository;

@Service
public class OglasService {

	@Autowired
	OglasRepository oglasRepository;
	
	public void remove(Long id) {
		oglasRepository.delete(id);
	}
	
	public Oglas findOne(Long id) {
		return oglasRepository.findOne(id);
	}
}
