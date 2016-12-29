package com.konstrukcija.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.KomentarDTO;
import com.konstrukcija.dto.OcenaDTO;
import com.konstrukcija.dto.OglasDTO;
import com.konstrukcija.model.Komentar;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Objavio;
import com.konstrukcija.model.Ocena;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.model.PrijavaOglasa;
import com.konstrukcija.repository.OglasRepository;
import com.konstrukcija.repository.PrijavaOglasaRepository;
import com.konstrukcija.service.KomentarService;
import com.konstrukcija.service.KorisnikService;
import com.konstrukcija.service.NekretnineService;
import com.konstrukcija.service.ObjavioService;
import com.konstrukcija.service.OcenaService;

@RestController
@RequestMapping(value ="api/oglas")
public class OglasController {

	//@Autowired
	//private OglasService oglasService;
	@Autowired
	private OglasRepository oglasRepository;
	
	@Autowired
	private NekretnineService nekretninaService;
	
	@Autowired
	private ObjavioService objavioService;
	
	@Autowired
	private OcenaService ocenaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KomentarService komentarService;
	
	@Autowired
	private PrijavaOglasaRepository prijavaOglasaRepository;
	
	/**
	 * Objava oglasa kako bi bila dostupa svim posjetiocima sajta
	 * @param idNekretnina, id nekretnine koju objavljujemo
	 * @param idObjavi, id onog ko je kreirao oglas radi laksis pretraga
	 * @param oglasDTO, podatak do kada vazi objava
	 * @return nekretnina je objavljena i vidljiva svim korisnicima sata
	 */
	@RequestMapping(value = "/add/{idNekretnina}/{idObjavi}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getObjavaNekretnine(Principal principal, @PathVariable Long idNekretnina, @PathVariable Long idObjavi, @RequestBody OglasDTO oglasDTO) {
		
		Nekretnina nekretnina = nekretninaService.findOne(idNekretnina);
		Oglas oglasPostoji = oglasRepository.findByNekretnina(nekretnina);
		
		if(oglasPostoji != null) {
			return new ResponseEntity<>("ne moze vec postoji ",HttpStatus.OK);
		}
		
		Objavio objavio = objavioService.findOne(idObjavi);
		Oglas oglas = new Oglas();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		
		oglas.setDatum_objave(dateFormat.format(date));
		oglas.setDatum_azuriranja(dateFormat.format(date));
		oglas.setDatum_isteka(oglasDTO.getDatum_isteka());
		oglas.setNekretnina(nekretnina);
		oglas.setObjavio(objavio);
		
		oglasRepository.save(oglas);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Dodavanje ocene za dati oglas
	 * @param idOglas
	 * @param ocenaDTO
	 * @return 
	 */
	@RequestMapping(value = "/ocena/{idOglas}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getOcenaOglas(@PathVariable Long idOglas, @RequestBody OcenaDTO ocenaDTO) {
		
		Ocena ocena = new Ocena();
		ocena.setOcena(ocenaDTO.getOcena());
		ocena.setOglas(oglasRepository.findOne(idOglas));
		
		ocena = ocenaService.save(ocena);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Dodati komentar za dati oglas
	 * @param idOglas id oglasa kome dodajemo oglas
	 * @param idKorisnik, id korisnika koji pise komentar
	 * @param komentarDTO, komentar
	 * @return nakon davanja oglasa on ce se pojaviti ispod nekretnine
	 */
	@RequestMapping(value = "/komentar/{idOglas}/{idKorisnik}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getKomentarOglas(@PathVariable Long idOglas,@PathVariable Long idKorisnik, @RequestBody KomentarDTO komentarDTO) {
		
		Korisnik korisnik = korisnikService.findOne(idKorisnik);
		Komentar komentar = new Komentar();
		komentar.setKomentar(komentarDTO.getKomentar());
		komentar.setKorisnik(korisnik);
		komentar.setOglas(oglasRepository.findOne(idOglas));
		
		komentar = komentarService.save(komentar);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Prikaz odredjene nekretnine koja je objavljena
	 * @param idNekretnina id nekretnine koju prikazujemo
	 * @return svi potrebni podaci
	 */
	@RequestMapping(value = "/nekretnina/{idNekretnina}", method = RequestMethod.GET)
	public ResponseEntity<OglasDTO> getOneNekretnina(@PathVariable Long idNekretnina) {
		
		Nekretnina nekretnina = nekretninaService.findOne(idNekretnina);
		Oglas oglas = oglasRepository.findByNekretnina(nekretnina);
		
		
		return new ResponseEntity<>(new OglasDTO(oglas), HttpStatus.OK);
	}
	
	/**
	 * Prijava odredjenog oglasa
	 * @param idOglas oglas koji prijavljujemo
	 * @param idKorisnik korisnik koji prijavljuje oglas
	 * @return cuva se u bazu i stize notifikacija adminu da obrise oglas
	 */
	@RequestMapping(value = "/prijava/{idOglas}/{idKorisnik}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getPrijavaOglasa(@PathVariable Long idOglas, @PathVariable Long idKorisnik) {
		
		Oglas oglas = oglasRepository.findOne(idOglas);
		Korisnik korisnik = korisnikService.findOne(idKorisnik);
		PrijavaOglasa prijavaOglasa = new PrijavaOglasa();
		
		prijavaOglasa.setKorisnik(korisnik);
		prijavaOglasa.setOglas(oglas);
		prijavaOglasa.setPrijava(true);
		
		prijavaOglasaRepository.save(prijavaOglasa);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
