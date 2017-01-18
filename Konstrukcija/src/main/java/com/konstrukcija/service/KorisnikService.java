package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Korisnik;
import com.konstrukcija.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepository;
	
	//Ispis svih korisnika sacuvanih u bazi
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}
	
	//Logovanje korisnika treba implementirati do kraja
	public Korisnik findOneByUsernameAndPassword(String username, String password) {
		return korisnikRepository.findOneByUsernameAndPassword(username, password);
	}
	
	//Dodavanje novog korisnika
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}

	public Korisnik findByEmail(String email) {
		return korisnikRepository.findByEmail(email);
	}
	
	public Korisnik findOne(Long id) {
		return korisnikRepository.findOne(id);
	}
	
	public Korisnik findByVerifyCode(String verifyCode) {
		return korisnikRepository.findByVerifyCode(verifyCode);
	}
}
