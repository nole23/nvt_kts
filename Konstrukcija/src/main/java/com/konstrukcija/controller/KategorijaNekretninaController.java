package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.KategorijaNekretnineDTO;
import com.konstrukcija.model.Kategorija;
import com.konstrukcija.model.KategorijaNekretnine;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.service.KategorijaNekretnineService;
import com.konstrukcija.service.KategorijaService;
import com.konstrukcija.service.NekretnineService;

@RestController
@RequestMapping(value = "api/kategorija/nekretnine")
public class KategorijaNekretninaController {

	@Autowired
	private KategorijaNekretnineService kategorijaNekretnineService;
	
	@Autowired
	private KategorijaService kategorijaService;
	
	@Autowired
	private NekretnineService nekretninaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KategorijaNekretnineDTO>> getKategorijaNekretnine() {
		List<KategorijaNekretnine> kategorijaNekretnine = kategorijaNekretnineService.findAll();
		
		List<KategorijaNekretnineDTO> kategorijaNekretnineDTO = new ArrayList<>();
		for(KategorijaNekretnine kn : kategorijaNekretnine) {
			kategorijaNekretnineDTO.add(new KategorijaNekretnineDTO(kn));
		}
		return new ResponseEntity<>(kategorijaNekretnineDTO, HttpStatus.OK);
	}
	
	//Dodavanje katNek
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<KategorijaNekretnineDTO> createKatNekretnine(@RequestBody KategorijaNekretnineDTO kategorijaNekretnineDTO) {
		
		if(kategorijaNekretnineDTO.getKategorija() == null || kategorijaNekretnineDTO.getNekretnina() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Kategorija kategorija = kategorijaService.findOne(kategorijaNekretnineDTO.getKategorija().getId());
		Nekretnina nekretnina = nekretninaService.findOne(kategorijaNekretnineDTO.getNekretnina().getId());
		
		if(kategorija == null || nekretnina == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		KategorijaNekretnine kategorijaNekretnina = new KategorijaNekretnine();
		kategorijaNekretnina.setKategorija(kategorija);
		kategorijaNekretnina.setNekretnina(nekretnina);
		
		kategorijaNekretnina = kategorijaNekretnineService.save(kategorijaNekretnina);
		return new ResponseEntity<>(new KategorijaNekretnineDTO(kategorijaNekretnina), HttpStatus.CREATED);
	}
}
