package com.konstrukcija.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.KompanijaDTO;
import com.konstrukcija.model.Kompanija;
import com.konstrukcija.model.Zaposleni;
import com.konstrukcija.repository.KompanijaRepository;
import com.konstrukcija.repository.KorisnikRepository;
import com.konstrukcija.repository.ZaposleniRepository;

/**
 * 
 * @author X
 *	
 * Registracija nove kompanije. Kompaniju moze dodati registrovani korisnik i samim tim postaje 
 * zaposlen u toj kompaniji. On moze da dodaje nove zaposlene
 *
 */
@RestController
@RequestMapping(value = "api/kompanija")
public class KompanijaController {

	@Autowired
	private KompanijaRepository kompanijaRepository;
	
	@Autowired
	private ZaposleniRepository zaposleniRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@RequestMapping(value="/company/{idKorisnik}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> saveKompanija(@PathVariable Long idKorisnik, @RequestBody KompanijaDTO kompanijaDTO) {
		
		Kompanija kompanija = new Kompanija();
		Zaposleni zaposleni = new Zaposleni();
		
		kompanija.setNazivKomapnije(kompanijaDTO.getNazivKompanije());
		kompanija.setLink(kompanijaDTO.getLink());
		kompanija.setAdresa(null);
		
		zaposleni.setKorisnik(korisnikRepository.findOne(idKorisnik));
		zaposleni.setKompanija(kompanija);
		
		kompanija = kompanijaRepository.save(kompanija);
		zaposleniRepository.save(zaposleni);
		
		return new ResponseEntity<>("Add company",HttpStatus.OK);
	}
	
	@RequestMapping(value="/company/new/{idAdd}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updateZaposleniKompanija(@PathVariable Long idAdd, @RequestBody KompanijaDTO kompanijaDTO){
		
		Kompanija kompanija = kompanijaRepository.findOne(kompanijaDTO.getId());
		Zaposleni zaposleni = new Zaposleni();
		
		zaposleni.setKorisnik(korisnikRepository.findOne(idAdd));
		zaposleni.setKompanija(kompanija);
		
		zaposleniRepository.save(zaposleni);
		
		return new ResponseEntity<>("Add zaposleni",HttpStatus.OK);
		
		
	}
}
