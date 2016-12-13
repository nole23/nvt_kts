package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.repository.NekretninaRepository;

@Service
public class NekretnineService {

	@Autowired
	NekretninaRepository nekretninaRepository;
	
	public List<Nekretnina> findAll() {
		return nekretninaRepository.findAll();
	}
}
