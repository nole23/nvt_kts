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

import com.konstrukcija.dto.ObjavaDTO;
import com.konstrukcija.model.Objava;
import com.konstrukcija.service.ObjavaService;
/**
 * 
 * @author X
 * 
 */
@RestController
@RequestMapping(value = "api/objave")
public class ObjavaController {
	
	@Autowired
	private ObjavaService objavaService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ObjavaDTO>> getAllObjave() {
		List<Objava> objava = objavaService.findAll();
		
		List<ObjavaDTO> objavaDTO = new ArrayList<>();
		for(Objava o : objava) {
			objavaDTO.add(new ObjavaDTO(o));
		}
		return new ResponseEntity<>(objavaDTO, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @param idNekretnine
	 * @param idKompanije
	 * @param objavaDTO
	 * @return saveObjava na osnovu ulaznih parametara
	 */
	@RequestMapping(value = "/{id}/{idNekretnine}/{idKompanije}", method = RequestMethod.GET)
	public ResponseEntity<String> createObjava(@PathVariable String id, @PathVariable String idNekretnine, @PathVariable String idKompanije, @RequestBody ObjavaDTO objavaDTO) {
		Objava objava = new Objava();
		if(objavaDTO.getKorisnik() == null){
			objava.setKompanija(objavaDTO.getKompanija());
			objava.setNekretnina(objavaDTO.getNekretnina());
			objava.setKorisnik(null);
			
			objava = objavaService.save(objava);
			
		} else if(objavaDTO.getKompanija() == null) {
			objava.setKompanija(null);
			objava.setKorisnik(objavaDTO.getKorisnik());
			objava.setNekretnina(objavaDTO.getNekretnina());
			
			objava = objavaService.save(objava);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
}
