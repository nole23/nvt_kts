package com.konstrukcija.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.konstrukcija.model.PrijavljeniOglas;
import com.konstrukcija.repository.PrijavljenOglasRepository;

@Service
public class PrijavljenOglasService {

	@Autowired
	PrijavljenOglasRepository prijavljenOglasRepository;
	
	public PrijavljeniOglas save(PrijavljeniOglas prijavljen) {
		return prijavljenOglasRepository.save(prijavljen);
	}
}
