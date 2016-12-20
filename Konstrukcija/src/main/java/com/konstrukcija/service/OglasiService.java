package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Oglasi;
import com.konstrukcija.repository.OglasiRepository;

@Service
public class OglasiService {

	@Autowired
	OglasiRepository oglasiRepository;
	
	public List<Oglasi> findAll() {
		return oglasiRepository.findAll();
	}
	
	public Oglasi save(Oglasi oglasi) {
		return oglasiRepository.save(oglasi);
	}
	
	public void remove(Long id) {
		oglasiRepository.delete(id);
	}
}
