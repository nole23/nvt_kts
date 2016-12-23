package com.konstrukcija.controller;

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
import com.konstrukcija.model.Komentari;
import com.konstrukcija.model.Ocene;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.service.OcenaService;
import com.konstrukcija.service.OglasService;

@RestController
@RequestMapping(value = "api/ocene")
public class OcenaController {

	@Autowired
	private OcenaService ocenaService;
	
	@Autowired
	private OglasService oglasService;
	
	/**
	 * 
	 * @param idOglas
	 * @param ocenaDTO
	 * @return dodavanje ocene za dati oglas @param idOglas
	 */
	
	@RequestMapping(value = "/add/{idOglas}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<OcenaDTO> saveOcene(@PathVariable Long idOglas, @RequestBody OcenaDTO ocenaDTO) {
		
		Oglas oglas = oglasService.findOne(idOglas);
		if(oglas == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
		}
		
		Ocene ocene = new Ocene();
		ocene.setOcena(ocenaDTO.getOcena());
		ocene.setOglas(oglasService.findOne(idOglas));
		
		ocene = ocenaService.save(ocene);
		
		return new ResponseEntity<>(new OcenaDTO(ocene), HttpStatus.CREATED);
	}
}
