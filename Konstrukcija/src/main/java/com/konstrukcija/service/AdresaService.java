package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Adresa;
import com.konstrukcija.repository.AdresaRepository;

@Service
public class AdresaService {

	@Autowired
	AdresaRepository adresaRepository;
	
	public List<Adresa> findAll() {
		return adresaRepository.findAll();
	}
	
	public Adresa save(Adresa adresa) {
		return adresaRepository.save(adresa);
	}
}
