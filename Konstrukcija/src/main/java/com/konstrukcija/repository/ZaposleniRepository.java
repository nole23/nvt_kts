package com.konstrukcija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Kompanija;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Zaposleni;

public interface ZaposleniRepository extends JpaRepository<Zaposleni, Long> {

	Zaposleni findByKorisnik(Korisnik korisnik);

	Zaposleni findByKompanija(Kompanija kompanija);

	List<Zaposleni> findAllById(Long id);

	List<Zaposleni> findAllByKompanija(Kompanija kompanija);

}
