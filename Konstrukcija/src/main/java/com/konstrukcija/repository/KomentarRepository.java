package com.konstrukcija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Komentari;

public interface KomentarRepository extends JpaRepository<Komentari, Long> {

	List<Komentari> findByOglas(String oglas);

}
