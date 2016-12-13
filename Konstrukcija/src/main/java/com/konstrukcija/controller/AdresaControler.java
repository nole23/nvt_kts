package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.AdresaDTO;
import com.konstrukcija.model.Adresa;
import com.konstrukcija.service.AdresaService;

@RestController
@RequestMapping(value="api/adresa")
public class AdresaControler {

	@Autowired
	private AdresaService adresaService;
	
	
	//Proveriti za adresu da li treba ovo
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AdresaDTO>> getAdresa() {
		List<Adresa> adresa = adresaService.findAll();
		
		List<AdresaDTO> adresaDTO = new ArrayList<>();
		for(Adresa s : adresa) {
			adresaDTO.add(new AdresaDTO(s));
		}
		return new ResponseEntity<>(adresaDTO, HttpStatus.OK);
	}
}
