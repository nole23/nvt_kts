package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Komentar;
import com.konstrukcija.repository.KomentarRepository;

@Service
public class KomentarService {
	
	@Autowired
	KomentarRepository komentariRepository;
	
	public List<Komentar> findAll() {
		return komentariRepository.findAll();
	}
	
	public Komentar save(Komentar komentar) {
		return komentariRepository.save(komentar);
	}
	
	public Komentar findOne(Long id) {
		return komentariRepository.findOne(id);
	}
	
}
