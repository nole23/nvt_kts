package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

	Korisnik findOneByUsernameAndPassword(String username, String password);
	Korisnik findByEmail(String email);
	Korisnik findByUsername(String username);
	Korisnik findOne(Long id);
	Korisnik findByVerifyCode(String verifyCode);

}
