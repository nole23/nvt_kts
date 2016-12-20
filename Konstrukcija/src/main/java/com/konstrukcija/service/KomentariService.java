package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Komentari;
import com.konstrukcija.repository.KomentarRepository;

@Service
public class KomentariService {

	@Autowired
	KomentarRepository komentarRepository;
	
	public List<Komentari> findALl() {
		return komentarRepository.findAll();
	}
	
	
	public List<Komentari> findAllByOglas(String oglas) {
		return komentarRepository.findByOglas(oglas);
	}
	
	public Komentari save(Komentari komentari) {
		return komentarRepository.save(komentari);
	}
}
