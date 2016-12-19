package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Lokacija;
import com.konstrukcija.repository.LokacijaRepository;

@Service
public class LokacijaService {

	@Autowired
	LokacijaRepository lokacijaRepository;
	
	public List<Lokacija> findAll() {
		return lokacijaRepository.findAll();
	}
	
	public Lokacija save(Lokacija lokacija) {
		return lokacijaRepository.save(lokacija);
	}
}
