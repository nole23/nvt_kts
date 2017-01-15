package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.UserAuthority;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {

	UserAuthority findByKorisnik(Korisnik korisnik);
}
