package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.LokacijaDTO;
import com.konstrukcija.model.Lokacija;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.service.LokacijaService;
import com.konstrukcija.service.NekretnineService;

/**
 * 
 * @author X
 * 
 * Dodavanje lokacije kao i gps pozicije za datu nekretninu
 */
@RestController
@RequestMapping(value="api/lokacija")
public class LokacijaController {

	@Autowired
	private LokacijaService lokacijaService;
	
	@Autowired
	private NekretnineService nekretninaService;
	
	//Dodavanje lokacije
	@RequestMapping(value = "/{idNekretnina}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> saveLokacija(@PathVariable Long idNekretnina, @RequestBody LokacijaDTO lokacijaDTO) {
		
		Nekretnina nekretnina = nekretninaService.findOne(idNekretnina);
		if(nekretnina == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Lokacija lokacija = new Lokacija();
		
		lokacija.setDrzava(lokacijaDTO.getDrzava());
		lokacija.setGrad(lokacijaDTO.getGrad());
		lokacija.setOblas(lokacijaDTO.getOblas());
		lokacija.setUlica(lokacijaDTO.getUlica());
		lokacija.setBrojPTT(lokacijaDTO.getBrojPTT());
		lokacija.setBrStana(lokacijaDTO.getBrStana());
		lokacija.setGeo_duzina(lokacijaDTO.getGeo_duzina());
		lokacija.setGeo_sirina(lokacijaDTO.getGeo_sirina());
		
		nekretnina.setLokacija(lokacija);
		
		lokacija = lokacijaService.save(lokacija);
		nekretnina = nekretninaService.save(nekretnina);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
