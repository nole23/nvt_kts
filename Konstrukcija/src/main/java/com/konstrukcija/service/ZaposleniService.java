package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Kompanija;
import com.konstrukcija.model.Zaposleni;
import com.konstrukcija.repository.ZaposleniRepository;

@Service
public class ZaposleniService {

	@Autowired
	ZaposleniRepository zaposleniRepository;
	
	public List<Zaposleni> findByKorisnikLong (Long id) {
		return zaposleniRepository.findAllById(id);
	}
	
	public List<Zaposleni> findByKompanija(Kompanija kompanija) {
		return zaposleniRepository.findAllByKompanija(kompanija);
	}

	public void remove(Long id) {
		zaposleniRepository.delete(id);
		
	}
}
