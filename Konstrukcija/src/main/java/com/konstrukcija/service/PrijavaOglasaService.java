package com.konstrukcija.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.konstrukcija.model.PrijavaOglasa;
import com.konstrukcija.repository.PrijavaOglasaRepository;

@Service
public class PrijavaOglasaService {

	@Autowired
	private PrijavaOglasaRepository prijavaOglasa;
	
	public PrijavaOglasa save(PrijavaOglasa prijava) {
		return prijavaOglasa.save(prijava);
	}
}
