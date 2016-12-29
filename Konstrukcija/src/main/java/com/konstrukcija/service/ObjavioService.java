package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Objavio;
import com.konstrukcija.repository.ObjavioRepository;

@Service
public class ObjavioService {

	@Autowired
	ObjavioRepository objavaRepository;
	
	public List<Objavio> findAll() {
		return objavaRepository.findAll();
	}
	
	public Objavio save(Objavio objavio) {
		return objavaRepository.save(objavio);
	}
	
	public Objavio findOne(Long id) {
		return objavaRepository.findOne(id);
	}
}
