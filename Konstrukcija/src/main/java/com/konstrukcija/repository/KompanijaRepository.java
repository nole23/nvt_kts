package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Kompanija;
import com.konstrukcija.model.Zaposleni;

public interface KompanijaRepository extends JpaRepository<Kompanija, Long> {

	Kompanija findByZaposleni(Zaposleni zaposleni);

}
