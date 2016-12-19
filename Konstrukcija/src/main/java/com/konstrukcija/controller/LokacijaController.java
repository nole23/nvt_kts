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

import com.konstrukcija.dto.LokacijaDTO;
import com.konstrukcija.model.Lokacija;
import com.konstrukcija.service.LokacijaService;

@RestController
@RequestMapping(value="api/lokacija")
public class LokacijaController {

	@Autowired
	private LokacijaService lokacijaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LokacijaDTO>> getLokacija() {
		List<Lokacija> lokacija = lokacijaService.findAll();
		
		List<LokacijaDTO> lokacijaDTO = new ArrayList<>();
		for(Lokacija l : lokacija) {
			lokacijaDTO.add(new LokacijaDTO(l));
		}
		return new ResponseEntity<>(lokacijaDTO, HttpStatus.OK);
	}
	
	//Dodavanje lokacije
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> saveLokacija(@RequestBody LokacijaDTO lokacijaDTO) {
		Lokacija lokacija = new Lokacija();
		
		lokacija.setDrzava(lokacijaDTO.getDrzava());
		lokacija.setGrad(lokacijaDTO.getGrad());
		lokacija.setOblas(lokacijaDTO.getOblas());
		lokacija.setUlica(lokacijaDTO.getUlica());
		lokacija.setBrojPTT(lokacijaDTO.getBrojPTT());
		lokacija.setBrStana(lokacijaDTO.getBrStana());
		lokacija.setGeo_duzina(lokacijaDTO.getGeo_duzina());
		lokacija.setGeo_sirina(lokacijaDTO.getGeo_sirina());
		
		lokacija = lokacijaService.save(lokacija);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
