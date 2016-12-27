package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.repository.OglasiRepository;
import com.konstrukcija.service.NekretnineService;

@RestController
@RequestMapping(value = "api/oglasi")
public class OglasiController {
	
	@Autowired
	private NekretnineService nekretninaService;
	
	@Autowired
	private OglasiRepository oglasiRepository;
	/*
	@RequestMapping(value = "/nekrenine/{}", method = RequestMethod.GET)
	public ResponseEntity<List<KategorijaDTO>> getKategorija() {
		List<Kategorija> kategorija = kategorijaService.findAll();
		
		List<KategorijaDTO> kategorijaDTO = new ArrayList<>();
		for(Kategorija k : kategorija) {
			kategorijaDTO.add(new KategorijaDTO(k));
		}
		return new ResponseEntity<>(kategorijaDTO, HttpStatus.OK);
	}
	*/
}
