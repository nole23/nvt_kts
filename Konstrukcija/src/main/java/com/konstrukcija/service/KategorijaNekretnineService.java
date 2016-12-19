package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.KategorijaNekretnine;
import com.konstrukcija.repository.KategorijaNekretnineRepository;

@Service
public class KategorijaNekretnineService {

	@Autowired
	KategorijaNekretnineRepository kategorijaNekretnineRepository;
	
	public List<KategorijaNekretnine> findAll() {
		return kategorijaNekretnineRepository.findAll();
	}
	
	//Dodavanje
	public KategorijaNekretnine save(KategorijaNekretnine kategorijaNekretnine) {
		return kategorijaNekretnineRepository.save(kategorijaNekretnine);
	}
	
	//brisanje
	public void remove(Long id) {
		kategorijaNekretnineRepository.delete(id);
	}
	
	//pretraga po idKategorije
	public Page<KategorijaNekretnine> findAll(Pageable page) {
		return kategorijaNekretnineRepository.findAll(page);
	}
}
