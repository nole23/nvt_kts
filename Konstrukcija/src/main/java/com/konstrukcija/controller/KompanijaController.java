package com.konstrukcija.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.AdresaDTO;
import com.konstrukcija.dto.KompanijaDTO;
import com.konstrukcija.model.Adresa;
import com.konstrukcija.model.Kompanija;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Zaposleni;
import com.konstrukcija.repository.AdresaRepository;
import com.konstrukcija.repository.KomentarRepository;
import com.konstrukcija.repository.KompanijaRepository;
import com.konstrukcija.repository.ZaposleniRepository;
import com.konstrukcija.service.KorisnikService;

@RestController
@RequestMapping(value = "api/kompanija")
public class KompanijaController {
	
	@Autowired 
	private KorisnikService korisnikService;
	
	@Autowired
	private ZaposleniRepository zaposleniRepositoy;
	
	@Autowired
	private AdresaRepository adresaService;
	
	@Autowired
	private KompanijaRepository kompanijaRepository;
	
	/**
	 * Dodati korisnika u u firmu kao zaposleni
	 * @param idKorisnik
	 * @param idKompanija
	 * @return
	 */
	@RequestMapping(value = "/{idKompanija}/{idKorisnik}", method = RequestMethod.GET)
	public ResponseEntity<String> addKorisnikKompanija(@PathVariable Long idKorisnik, @PathVariable Long idKompanija) {
		
		Korisnik korisnik = korisnikService.findOne(idKorisnik);
		//Kompanija kompanija = kompanijaService.findOne(idKompanija);
		Zaposleni zaposleni = new Zaposleni();
		
		zaposleni.setKompanija(kompanijaRepository.findOne(idKompanija));
		zaposleni.setKorisnik(korisnik);
		
		zaposleniRepositoy.save(zaposleni);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Kreiranje nove kompanije i automatski postajete njen admin
	 * @param idKorisnik
	 * @param kompanijaDTO
	 * @return
	 */
	@RequestMapping(value = "/add/kompanija/{idKorisnik}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> addKompanija(@PathVariable Long idKorisnik, @RequestBody KompanijaDTO kompanijaDTO) {
		
		Korisnik korisnik = korisnikService.findOne(idKorisnik);
		
		Kompanija kompanija = new Kompanija();
		Zaposleni zaposleni = new Zaposleni();
		
		kompanija.setNazivKomapnije(kompanijaDTO.getNazivKomapnije());
		kompanija.setLink(kompanijaDTO.getLink());
		
		zaposleni.setKompanija(kompanija);
		zaposleni.setKorisnik(korisnik);
		
		kompanijaRepository.save(kompanija);
		zaposleniRepositoy.save(zaposleni);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/kompanija/{idKompanija}/adresa", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getKompanijaAdresa(@PathVariable Long idKompanija, @RequestBody AdresaDTO adresaDTO) {
		
		Kompanija kompanija = kompanijaRepository.findOne(idKompanija);
		Adresa adresa = new Adresa();
		
		adresa.setDrzava(adresaDTO.getDrzava());
		adresa.setGrad(adresaDTO.getGrad());
		adresa.setUlica(adresaDTO.getUlica());
		adresa.setBroj_ulice(adresaDTO.getBroj_ulice());
		adresa.setBroj_zgrade(adresaDTO.getBroj_zgrade());
		adresa.setBroj_stama(adresaDTO.getBroj_stama());
		
		kompanija.setAdresa(adresa);
		
		adresaService.save(adresa);
		kompanijaRepository.save(kompanija);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
