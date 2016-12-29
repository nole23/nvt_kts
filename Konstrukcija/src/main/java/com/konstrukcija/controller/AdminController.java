package com.konstrukcija.controller;

import java.security.Principal;
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

import com.konstrukcija.dto.KategorijaDTO;
import com.konstrukcija.dto.KorisnikDTO;
import com.konstrukcija.model.Kategorija;
import com.konstrukcija.model.Lokacija;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Objavio;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.model.TehnickaOpremljenost;
import com.konstrukcija.repository.KategorijaRepository;
import com.konstrukcija.repository.ObjavioRepository;
import com.konstrukcija.repository.OglasRepository;
import com.konstrukcija.service.LokacijaService;
import com.konstrukcija.service.NekretnineService;
import com.konstrukcija.service.ObjavioService;
import com.konstrukcija.service.TehnickaOpremljenostService;

@RestController
@RequestMapping(value = "api/admin")
public class AdminController {
	
	@Autowired
	private KategorijaRepository kategorijaService;
	
	
	@Autowired
	private NekretnineService nekretninaService;
	
	@Autowired
	private TehnickaOpremljenostService tehnicakOpremljenostService;
	
	@Autowired
	private LokacijaService lokacijaService;
	
	@Autowired ObjavioRepository objavioRepository;
	
	@Autowired
	private ObjavioService objavioService;
	
	@Autowired
	private OglasRepository oglasService;
	
	
	/**
	 * Cuvanje nove kategorije, moze samo menadzer sistema da uradi
	 * @param principal
	 * @param kategorijaDTO, parametri kategorije
	 * @return dodata nova kategorija
	 */
	@RequestMapping(value = "/add/kategorija", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<KategorijaDTO> saveKategorija(Principal principal, @RequestBody KategorijaDTO kategorijaDTO) {
		
		Kategorija kategorija = new Kategorija();
		
		kategorija.setName(kategorijaDTO.getName());
		kategorija.setTip(kategorijaDTO.getTip());
		
		kategorijaService.save(kategorija);
		
		return new ResponseEntity<>(new KategorijaDTO(kategorija),HttpStatus.OK);
	}
	
	/**
	 * Pregled svih prijavljenih oglasa
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/prijavljena/nekretnina", method = RequestMethod.GET)
	public ResponseEntity<List<KategorijaDTO>> getPrijavljenaNekretnina(Principal principal) {
		
		List<Kategorija> kategorija = kategorijaService.findAll();
		
		List<KategorijaDTO> kategorijaDTO = new ArrayList<>();
		for(Kategorija k: kategorija) {
			kategorijaDTO.add(new KategorijaDTO(k));
		}
		
		return new ResponseEntity<>(kategorijaDTO,HttpStatus.OK);
	}
	
	/**
	 * Metoda koja brise datu nekretninu
	 * @param principal
	 * @param idNekretnina nekretnina koju trebamo da obrisemo
	 * @return pre nego se izbirse nekretnina treba izbrisati podatke iz zavisnih tabela kao sto su
	 * lokacija, tehnickaOpremljenost, objavio i oglas
	 */
	@RequestMapping(value = "/delete/nekretnina/{idNekretnina}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteNekretnina(Principal principal, @PathVariable Long idNekretnina) {
		
		Nekretnina nekretnina = nekretninaService.findOne(idNekretnina);
		TehnickaOpremljenost tehnicka = nekretnina.getTehnickaOpremljenost();
		Lokacija lok = nekretnina.getLokacija();
		Objavio objavio = objavioRepository.findByNekretnina(nekretnina);
		Oglas oglas = oglasService.findByNekretnina(nekretnina);
			
		if(tehnicka != null) {
			Long idTehnicka = tehnicka.getId();
			
			nekretnina.setTehnickaOpremljenost(null);
			nekretnina = nekretninaService.save(nekretnina);
			tehnicakOpremljenostService.remove(idTehnicka);
			if(lok != null) {
				Long idLokacija = lok.getId();
				nekretnina.setLokacija(null);
				nekretnina = nekretninaService.save(nekretnina);
				lokacijaService.remove(idLokacija);
			} if(oglas != null) {
				Long idOglas = oglas.getId();
				oglasService.delete(idOglas);
				objavio.setOglas(null);
				objavioService.save(objavio);
				if(objavio != null) {
					Long idObjavio = objavio.getId();
					objavioService.remove(idObjavio);
					if(nekretnina != null){
						nekretninaService.remove(idNekretnina);
						return new ResponseEntity<>(HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
	}
	
}
