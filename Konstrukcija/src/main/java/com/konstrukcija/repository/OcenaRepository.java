package com.konstrukcija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Ocene;

public interface OcenaRepository extends JpaRepository<Ocene, Long> {

	List<Ocene> findAllByOglas(Long oglas);

}
