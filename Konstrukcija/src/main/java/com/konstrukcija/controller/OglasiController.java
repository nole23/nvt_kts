package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.OglasiDTO;
import com.konstrukcija.model.Oglasi;
import com.konstrukcija.service.OglasiService;

@RestController
@RequestMapping(value="api/oglasi")
public class OglasiController {

	@Autowired
	OglasiService oglasiService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<OglasiDTO>> findAllOglasi() {
		List<Oglasi> oglasi = oglasiService.findAll();
		
		List<OglasiDTO> oglasiDTO = new ArrayList<>();
		for(Oglasi o : oglasi) {
			oglasiDTO.add(new OglasiDTO(o));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//Cuvanje novog oglasa
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<OglasiDTO> saveOglasi(@RequestBody OglasiDTO oglasiDTO) {
		Oglasi oglasi = new Oglasi();
		
		oglasi.setOglas(oglasiDTO.getOglas());
		oglasi.setNekretnina(oglasiDTO.getNekretnina());
		
		oglasi = oglasiService.save(oglasi);
		return new ResponseEntity<>(new OglasiDTO(oglasi), HttpStatus.CREATED);
		
	}
}
