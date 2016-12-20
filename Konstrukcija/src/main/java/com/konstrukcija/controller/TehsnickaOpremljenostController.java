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

import com.konstrukcija.dto.TehnickaOpremljenostDTO;
import com.konstrukcija.model.TehnickaOpremljenost;
import com.konstrukcija.service.TehnickaOpremljenostService;

@RestController
@RequestMapping(value = "api/opremljenost")
public class TehsnickaOpremljenostController {
	
	@Autowired
	private TehnickaOpremljenostService tehnickaOpremljenostService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<TehnickaOpremljenostDTO>> getAllOpremljenost() {
		List<TehnickaOpremljenost> tehnickaOpremljenost = tehnickaOpremljenostService.findAll();
		
		List<TehnickaOpremljenostDTO> tehnickaOpremljenostDTO = new ArrayList<>();
		for(TehnickaOpremljenost to : tehnickaOpremljenost) {
			tehnickaOpremljenostDTO.add(new TehnickaOpremljenostDTO(to));
		}
		return new ResponseEntity<>(tehnickaOpremljenostDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> saveOpremljenist(@RequestBody TehnickaOpremljenostDTO tehnickaOpremljenostDTO) {
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
		
		tehnickaOpremljenost = tehnickaOpremljenostService.save(tehnickaOpremljenost);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
