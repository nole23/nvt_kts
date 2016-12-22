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

import com.konstrukcija.dto.AdresaDTO;
import com.konstrukcija.model.Adresa;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.service.AdresaService;
import com.konstrukcija.service.KomentariService;
import com.konstrukcija.service.KorisnikService;

@RestController
@RequestMapping(value="api/adresa")
public class AdresaControler {

	@Autowired
	private AdresaService adresaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	
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
	
	//dodavanje nove adrese i cuvanje korisika kom pripada
	@RequestMapping(value="/adresa/{userID}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> saveAdres(@PathVariable Long userID, @RequestBody AdresaDTO adresaDTO) {
		Korisnik korisnik = korisnikService.findOne(userID);
		Adresa adresa = new Adresa();
		
		if(korisnik != null) {
			
			
			adresa.setDrzava(adresaDTO.getDrzava());
			adresa.setGrad(adresaDTO.getGrad());
			adresa.setUlica(adresaDTO.getUlica());
			adresa.setBroj(adresaDTO.getBroj());
			
			korisnik.setAdresa(adresa);
			
			adresa = adresaService.save(adresa);
			korisnik = korisnikService.save(korisnik);
			return new ResponseEntity<>("Sacucali ste novu adresu", HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>("Korisnik ne postoji", HttpStatus.BAD_REQUEST);
		
	}
	
}
