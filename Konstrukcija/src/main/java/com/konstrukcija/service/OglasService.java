package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Oglas;
import com.konstrukcija.repository.OglasRepository;

@Service
public class OglasService {

	@Autowired
	OglasRepository oglasRepository;
	
	public List<Oglas> fidnAll() {
		return oglasRepository.findAll();
	}
	
	public Oglas findOne(Long id) {
		return oglasRepository.findOne(id);
	}
	
	public Oglas save(Oglas oglas) {
		return oglasRepository.save(oglas);
	}
	
	public void remove(Long id) {
		oglasRepository.delete(id);
	}
}
