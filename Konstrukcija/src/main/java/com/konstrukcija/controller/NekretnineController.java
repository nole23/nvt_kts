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

import com.konstrukcija.dto.NekretninaDTO;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Objava;
import com.konstrukcija.repository.KompanijaRepository;
import com.konstrukcija.repository.KorisnikRepository;
import com.konstrukcija.repository.ObjavaRepository;
import com.konstrukcija.service.NekretnineService;

/**
 * 
 * @author X
 * Dodavanje nove nekretnine
 * @param vrstaNekretnine, idKorisnik, idKopanija
 * @return sacuvan u bazi nova nekretnina u zavisnosti ko ju je kreirao.
 */
@RestController
@RequestMapping(value = "api/nekretnineee")
public class NekretnineController {
	
	@Autowired
	private NekretnineService nekretninaService;

	@Autowired
	private ObjavaRepository objavioRepository;
	
	
	//@Autowired
	//private KategorijaRepository kategorijaRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private KompanijaRepository kompanijaRepository;

	//Ispis svih nekretnina
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<NekretninaDTO>> getAllNekretnina() {
		List<Nekretnina> nekrenine = nekretninaService.findAll();
		
		List<NekretninaDTO> nekretnineDTO = new ArrayList<>();
		for (Nekretnina n : nekrenine) {
			nekretnineDTO.add(new NekretninaDTO(n));
		}
		return new ResponseEntity<>(nekretnineDTO, HttpStatus.OK);
	}
	

	/**
	 * 
	 * @param nazivKat
	 * @param idKompanija
	 * @param idKorisnik
	 * @param nekretninaDTO
	 * @return saveNekretnina na osnovu ulaznih parametara i ispitivanje da li je korisnik ili kompanija
	 * 	objavio oglas
	 */
	@RequestMapping(value = "/{nazivKat}/{idKorisnik}/{idKompanija}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> saveNekretnine(@PathVariable String nazivKat, @PathVariable String idKompanija, @PathVariable String idKorisnik, @RequestBody NekretninaDTO nekretninaDTO) {
		if(idKompanija.equals("null")){
			
			Long id = Long.parseLong(idKorisnik);
			
			if(nazivKat.equals("prodaja")) {
				
				Nekretnina nekretnina;
				//KategorijaNekretnina katNekretnina = new KategorijaNekretnina();
				Objava objavio = new Objava();
				
				
				nekretnina = new Nekretnina();
				
				nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
				nekretnina.setCena(nekretninaDTO.getCena());
				nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
				nekretnina.setSobnost(nekretninaDTO.getSobnost());
				nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
				nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
				nekretnina.setSpratova(nekretninaDTO.getSpratova());
				nekretnina.setStanje(nekretninaDTO.getStanje());
				nekretnina.setSprat(nekretninaDTO.getSprat());
				nekretnina.setOpis(nekretninaDTO.getOpis());
				
				//katNekretnina.setKategorija(kategorijaRepository.findByName(("prodaja")));
				//katNekretnina.setNekretnina(nekretnina);
				
				objavio.setKompanija(null);
				objavio.setKorisnik(korisnikRepository.findOne(id));
				objavio.setNekretnina(nekretnina);
				
				nekretnina = nekretninaService.save(nekretnina);
				//kategorijaNekretnineRepository.save(katNekretnina);
				objavioRepository.save(objavio);
				
				return new ResponseEntity<>("korisnik "+id,HttpStatus.OK);
			
			} else {
				Nekretnina nekretnina;
				//KategorijaNekretnina katNekretnina = new KategorijaNekretnina();
				Objava objavio = new Objava();
				
				
				nekretnina = new Nekretnina();
				
				nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
				nekretnina.setCena(nekretninaDTO.getCena());
				nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
				nekretnina.setSobnost(nekretninaDTO.getSobnost());
				nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
				nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
				nekretnina.setSpratova(nekretninaDTO.getSpratova());
				nekretnina.setStanje(nekretninaDTO.getStanje());
				nekretnina.setSprat(nekretninaDTO.getSprat());
				nekretnina.setOpis(nekretninaDTO.getOpis());
				
				//katNekretnina.setKategorija(kategorijaRepository.findByName(("izdavanje")));
				//katNekretnina.setNekretnina(nekretnina);
				
				objavio.setKompanija(null);
				objavio.setKorisnik(korisnikRepository.findOne(id));
				objavio.setNekretnina(nekretnina);
				
				nekretnina = nekretninaService.save(nekretnina);
				//kategorijaNekretnineRepository.save(katNekretnina);
				objavioRepository.save(objavio);
				
				return new ResponseEntity<>("korisnik "+id,HttpStatus.OK);
			}
		} else if(idKorisnik.equals("null")) {
			
			Long id = Long.parseLong(idKompanija);
			
			if(nazivKat.equals("prodaja")) {
				
				Nekretnina nekretnina;
				//KategorijaNekretnina katNekretnina = new KategorijaNekretnina();
				Objava objavio = new Objava();
				
				
				nekretnina = new Nekretnina();
				
				nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
				nekretnina.setCena(nekretninaDTO.getCena());
				nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
				nekretnina.setSobnost(nekretninaDTO.getSobnost());
				nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
				nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
				nekretnina.setSpratova(nekretninaDTO.getSpratova());
				nekretnina.setStanje(nekretninaDTO.getStanje());
				nekretnina.setSprat(nekretninaDTO.getSprat());
				nekretnina.setOpis(nekretninaDTO.getOpis());
				
				//katNekretnina.setKategorija(kategorijaRepository.findByName(("prodaja")));
				//katNekretnina.setNekretnina(nekretnina);
				
				objavio.setKompanija(kompanijaRepository.findOne(id));
				objavio.setKorisnik(null);
				objavio.setNekretnina(nekretnina);
				
				nekretnina = nekretninaService.save(nekretnina);
				//kategorijaNekretnineRepository.save(katNekretnina);
				objavioRepository.save(objavio);
				
				return new ResponseEntity<>("korisnik "+id,HttpStatus.OK);
			
			} else {
				Nekretnina nekretnina;
				//KategorijaNekretnina katNekretnina = new KategorijaNekretnina();
				Objava objavio = new Objava();
				
				
				nekretnina = new Nekretnina();
				
				nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
				nekretnina.setCena(nekretninaDTO.getCena());
				nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
				nekretnina.setSobnost(nekretninaDTO.getSobnost());
				nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
				nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
				nekretnina.setSpratova(nekretninaDTO.getSpratova());
				nekretnina.setStanje(nekretninaDTO.getStanje());
				nekretnina.setSprat(nekretninaDTO.getSprat());
				nekretnina.setOpis(nekretninaDTO.getOpis());
				
				//katNekretnina.setKategorija(kategorijaRepository.findByName(("izdavanje")));
				//katNekretnina.setNekretnina(nekretnina);
				
				objavio.setKompanija(kompanijaRepository.findOne(id));
				objavio.setKorisnik(null);
				objavio.setNekretnina(nekretnina);
				
				nekretnina = nekretninaService.save(nekretnina);
				//kategorijaNekretnineRepository.save(katNekretnina);
				objavioRepository.save(objavio);
				
				return new ResponseEntity<>("korisnik "+id,HttpStatus.OK);
			}
			
		} else  {
			return new ResponseEntity<String>("Lose",HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
