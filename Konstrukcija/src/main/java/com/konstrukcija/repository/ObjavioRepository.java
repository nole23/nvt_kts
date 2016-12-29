package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Objavio;

public interface ObjavioRepository extends JpaRepository<Objavio, Long> {

	Objavio findByNekretnina(Nekretnina nekretnina);

}