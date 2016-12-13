package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
