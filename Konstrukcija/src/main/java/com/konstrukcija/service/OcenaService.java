package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Ocene;
import com.konstrukcija.repository.OcenaRepository;
import com.konstrukcija.repository.OglasRepository;

@Service
public class OcenaService {

	@Autowired
	OcenaRepository ocenaRepository;
	
	@Autowired
	OglasRepository oglasRepository;
	
	public List<Ocene> findAll() {
		return ocenaRepository.findAll();
	}
	
	public List<Ocene> findAllByIdOglas(Long oglas) {
		return ocenaRepository.findAllByOglas(oglas);
	}
	
	public Ocene save(Ocene ocene) {
		return ocenaRepository.save(ocene);
	}
}
