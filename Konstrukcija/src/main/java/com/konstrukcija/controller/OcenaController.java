package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.OcenaDTO;
import com.konstrukcija.model.Ocene;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.service.OcenaService;
import com.konstrukcija.service.OglasService;

@RestController
@RequestMapping(value = "api/ocene")
public class OcenaController {

	@Autowired
	OcenaService ocenaService;
	
	@Autowired
	OglasService oglasService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<OcenaDTO>> getFindAll() {
		List<Ocene> ocene = ocenaService.findAll();
		
		List<OcenaDTO> ocenaDTO = new ArrayList<>();
		for(Ocene o : ocene) {
			ocenaDTO.add(new OcenaDTO(o));
		}
		return new ResponseEntity<>(ocenaDTO, HttpStatus.OK);
	}
	
	//Prikaz svih ocena za dati idOglas
	@RequestMapping(value = "/ocenee", method = RequestMethod.GET)
	public ResponseEntity<List<OcenaDTO>> getOcenaByOglas(@RequestBody String oglasi) {
		
		Oglas oglas = oglasService.findOne(Long.parseLong(oglasi));
		
		Set<Ocene> ocena = oglas.getOcene();
		List<OcenaDTO> oceneDTO = new ArrayList<>();
		for(Ocene o : ocena) {
			OcenaDTO ocenaDTO = new OcenaDTO();
			
			ocenaDTO.setId(o.getId());
			ocenaDTO.setOcena(o.getOcena());
			ocenaDTO.setOglas(o.getOglas());
			
			oceneDTO.add(ocenaDTO);
		}
		return new ResponseEntity<>(oceneDTO, HttpStatus.OK);
	}
	
	//Cuvanje nove ocene
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<OcenaDTO> saveOcena(@RequestBody OcenaDTO ocenaDTO) {
		Ocene ocene = new Ocene();
		
		ocene.setOcena(ocenaDTO.getOcena());
		ocene.setOglas(ocenaDTO.getOglas());
		
		ocene = ocenaService.save(ocene);
		
		return new ResponseEntity<>(new OcenaDTO(ocene), HttpStatus.CREATED);
	}
}
