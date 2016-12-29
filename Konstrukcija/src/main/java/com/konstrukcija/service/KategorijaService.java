package com.konstrukcija.service;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.konstrukcija.model.Kategorija;
import com.konstrukcija.repository.KategorijaRepository;

@Service
public class KategorijaService {
	
	@Autowired
	KategorijaRepository kategorijaRepository;
	
	public List<Kategorija> findAll() {
		return kategorijaRepository.findAll();
	} 
	
	public Kategorija findByOneName(String name) {
		return kategorijaRepository.findByName(name);
	}
	public Kategorija findByOneTip(String tip) {
		return kategorijaRepository.findByTip(tip);
	}
	
	public Kategorija save(Kategorija kategorija) {
		return kategorijaRepository.save(kategorija);
	}
	
	public void remove(Long id) {
		kategorijaRepository.delete(id);
	}
}
