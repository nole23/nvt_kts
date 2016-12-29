package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Oglas;

public interface OglasRepository extends JpaRepository<Oglas, Long> {

	Oglas findByNekretnina(Nekretnina nekretnina);
}
