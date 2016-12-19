package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

	Korisnik findOneByEmailAndPass(String email, String pass);

	Korisnik findByEmail(String email);

	
}
