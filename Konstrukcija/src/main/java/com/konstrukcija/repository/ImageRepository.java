package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Image;
import com.konstrukcija.model.Nekretnina;

public interface ImageRepository extends JpaRepository<Image, Long> {

	Image findByNekretnina(Nekretnina nekretnina);
}
