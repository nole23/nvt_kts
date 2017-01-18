package com.konstrukcija.service;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.konstrukcija.dto.OglasDTO;
import com.konstrukcija.model.Nekretnina;
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

	public List<Oglas> findAll() {
		return oglasRepository.findAll();
	}
}
