package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Komentar;

public interface KomentarRepository extends JpaRepository<Komentar, Long> {
	
}
