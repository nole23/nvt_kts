package com.konstrukcija.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.konstrukcija.model.Kategorija;
import com.konstrukcija.repository.KategorijaRepository;

@Service
public class KategorijaService {
	
	@Autowired
	KategorijaRepository kategorijaRepository;
	
	public Kategorija findByOneName(String name) {
		return kategorijaRepository.findByName(name);
	}
	public Kategorija findByOneTip(String tip) {
		return kategorijaRepository.findByTip(tip);
	}
	
}
