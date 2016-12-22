package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {

	Kategorija findByName(String name);
}
