package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Komentari;
import com.konstrukcija.repository.KomentarRepository;

@Service
public class KomentariService {
	
	@Autowired
	KomentarRepository komentariRepository;
	
	public List<Komentari> findAll() {
		return komentariRepository.findAll();
	}
	
	public Komentari save(Komentari komentari) {
		return komentariRepository.save(komentari);
	}
	
	public Komentari findOne(Long id) {
		return komentariRepository.findOne(id);
	}
	
}
