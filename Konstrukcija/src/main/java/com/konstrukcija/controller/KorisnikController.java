package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.KorisnikDTO;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.service.KorisnikService;

@RestController
@RequestMapping(value="api/korisnik")
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikServer;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getKorisnik() {
		List<Korisnik> korisnik = korisnikServer.findAll();
		
		List<KorisnikDTO> korisnikDTO = new ArrayList<>();
		for(Korisnik k : korisnik) {
			korisnikDTO.add(new KorisnikDTO(k));
		}
		return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
	}
}
