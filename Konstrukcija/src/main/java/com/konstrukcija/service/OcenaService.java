package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Ocene;
import com.konstrukcija.repository.OcenaRepository;

@Service
public class OcenaService {
	
	@Autowired
	OcenaRepository ocenaRepository;
	
	public List<Ocene> findAll() {
		return ocenaRepository.findAll();
	}
	
	public Ocene save(Ocene ocene) {
		return ocenaRepository.save(ocene);
	}
	
	public Page<Ocene> findAll(Pageable page) {
		return ocenaRepository.findAll(page);
	}

}
