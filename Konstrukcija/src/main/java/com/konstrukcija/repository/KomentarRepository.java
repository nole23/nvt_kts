package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Komentari;

public interface KomentarRepository extends JpaRepository<Komentari, Long> {
	
}
