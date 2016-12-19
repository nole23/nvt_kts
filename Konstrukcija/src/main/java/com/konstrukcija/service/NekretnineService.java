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
	
	//Citanje svih nekretnina iz baze
	public List<Nekretnina> findAll() {
		return nekretninaRepository.findAll();
	}
	
	//Cuvanje nove nekretnine
	public Nekretnina save(Nekretnina nekretnina) {
		return nekretninaRepository.save(nekretnina);
	}
	
	//Brisanje odredjene nekretnine
	public void remove(Long id) {
		nekretninaRepository.delete(id);
	}
	
	public Nekretnina findOne(Long id) {
		return nekretninaRepository.findOne(id);
	}


	
}
