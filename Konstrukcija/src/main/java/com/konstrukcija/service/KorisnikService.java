package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Korisnik;
import com.konstrukcija.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepository;
	
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}
}
