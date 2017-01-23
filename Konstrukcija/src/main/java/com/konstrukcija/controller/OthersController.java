package com.konstrukcija.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.KategorijaDTO;
import com.konstrukcija.dto.ObjavioDTO;
import com.konstrukcija.model.Kategorija;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Objavio;
import com.konstrukcija.repository.KategorijaRepository;
import com.konstrukcija.repository.ObjavioRepository;
import com.konstrukcija.service.KorisnikService;

@RestController
@RequestMapping(value ="api/others")
public class OthersController {

	@Autowired
	private KategorijaRepository kategorijaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private ObjavioRepository objavioRepository;

	@RequestMapping(value="/kategorija/all", method = RequestMethod.GET)
	public ResponseEntity<List<KategorijaDTO>> getKategorija() {
		List<Kategorija> kategorija = kategorijaService.findAll();
		
		List<KategorijaDTO> kategorijaDTO = new ArrayList<>();
		for(Kategorija k : kategorija) {
			kategorijaDTO.add(new KategorijaDTO(k));
		}
		return new ResponseEntity<>(kategorijaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/oglasi/korisnika", method = RequestMethod.GET)
	public ResponseEntity<List<ObjavioDTO>> getOglasi(Principal principal) {
		
		Korisnik korisnik = korisnikService.findByUsername(principal.getName());
		
		List<Objavio> objave = objavioRepository.findByKorisnik(korisnik);
		
		List<ObjavioDTO> objavioDTO = new ArrayList<>();
		for(Objavio o : objave) {
			objavioDTO.add(new ObjavioDTO(o));
		}
		return new ResponseEntity<>(objavioDTO, HttpStatus.OK);
	}
}
