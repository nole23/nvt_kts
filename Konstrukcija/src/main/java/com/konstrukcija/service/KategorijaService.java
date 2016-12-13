package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Kategorija;
import com.konstrukcija.repository.KategorijaRepository;

@Service
public class KategorijaService {

	@Autowired
	KategorijaRepository kategorijaRepository;
	
	public List<Kategorija> findAll() {
		return kategorijaRepository.findAll();
	}
}
