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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.TehnickaOpremljenostDTO;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.TehnickaOpremljenost;
import com.konstrukcija.service.NekretnineService;
import com.konstrukcija.service.TehnickaOpremljenostService;

@RestController
@RequestMapping(value = "api/opremljenost")
public class TehsnickaOpremljenostController {
	
	@Autowired
	private TehnickaOpremljenostService tehnickaOpremljenostService;
	
	@Autowired
	private NekretnineService nekretninaService;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<TehnickaOpremljenostDTO>> getAllOpremljenost() {
		List<TehnickaOpremljenost> tehnickaOpremljenost = tehnickaOpremljenostService.findAll();
		
		List<TehnickaOpremljenostDTO> tehnickaOpremljenostDTO = new ArrayList<>();
		for(TehnickaOpremljenost to : tehnickaOpremljenost) {
			tehnickaOpremljenostDTO.add(new TehnickaOpremljenostDTO(to));
		}
		return new ResponseEntity<>(tehnickaOpremljenostDTO, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param idNekretnina
	 * @param tehnickaOpremljenostDTO
	 * @return azuziranje tehnicke opremljenosti za datu nekretninu
	 */
	@RequestMapping(value = "/{idNekretnina}", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> saveOpremljenist(@PathVariable Long idNekretnina, @RequestBody TehnickaOpremljenostDTO tehnickaOpremljenostDTO) {
		
		Nekretnina nekretnina = nekretninaService.findOne(idNekretnina);
		
		if(nekretnina == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		TehnickaOpremljenost tehnickaOpremljenost = new TehnickaOpremljenost();
		
		tehnickaOpremljenost.setTarasa(tehnickaOpremljenostDTO.getTarasa());
		tehnickaOpremljenost.setTelefon(tehnickaOpremljenostDTO.getTelefon());
		tehnickaOpremljenost.setGaraua(tehnickaOpremljenostDTO.getGaraua());
		tehnickaOpremljenost.setKablovska(tehnickaOpremljenostDTO.getKablovska());
		tehnickaOpremljenost.setPogled_na_grad(tehnickaOpremljenostDTO.getPogled_na_grad());
		tehnickaOpremljenost.setPodrum(tehnickaOpremljenostDTO.getPodrum());
		tehnickaOpremljenost.setKamin(tehnickaOpremljenostDTO.getKamin());
		tehnickaOpremljenost.setBazen(tehnickaOpremljenostDTO.getBazen());
		tehnickaOpremljenost.setInternet(tehnickaOpremljenostDTO.getInternet());
		tehnickaOpremljenost.setTavan(tehnickaOpremljenostDTO.getTavan());
		tehnickaOpremljenost.setKlima(tehnickaOpremljenostDTO.getKlima());
		tehnickaOpremljenost.setPogled_na_more(tehnickaOpremljenostDTO.getPogled_na_more());
		tehnickaOpremljenost.setLift(tehnickaOpremljenostDTO.getLift());
		tehnickaOpremljenost.setStrija(tehnickaOpremljenostDTO.getStrija());
		tehnickaOpremljenost.setVoda(tehnickaOpremljenostDTO.getVoda());
		tehnickaOpremljenost.setKanalizacija(tehnickaOpremljenostDTO.getKanalizacija());
		tehnickaOpremljenost.setGas(tehnickaOpremljenostDTO.getGas());
		
		nekretnina.setTehnickaOpremljenost(tehnickaOpremljenost);
		
		
		tehnickaOpremljenost = tehnickaOpremljenostService.save(tehnickaOpremljenost);
		nekretnina = nekretninaService.save(nekretnina);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
