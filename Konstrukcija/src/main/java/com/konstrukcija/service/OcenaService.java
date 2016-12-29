package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Ocena;
import com.konstrukcija.repository.OcenaRepository;

@Service
public class OcenaService {
	
	@Autowired
	OcenaRepository ocenaRepository;
	
	public List<Ocena> findAll() {
		return ocenaRepository.findAll();
	}
	
	public Ocena save(Ocena ocena) {
		return ocenaRepository.save(ocena);
	}
	
	public Page<Ocena> findAll(Pageable page) {
		return ocenaRepository.findAll(page);
	}
	
	public Ocena findOne(Long id) {
		return ocenaRepository.findOne(id);
	}
}
