package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Ocene;

public interface OcenaRepository extends JpaRepository<Ocene, Long> {

}
