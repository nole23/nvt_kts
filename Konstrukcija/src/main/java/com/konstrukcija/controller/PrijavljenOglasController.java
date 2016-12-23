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
import com.konstrukcija.dto.PrijavljeniOglasDTO;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.model.PrijavljeniOglas;
import com.konstrukcija.service.OglasService;
import com.konstrukcija.service.PrijavljenOglasService;

@RestController
@RequestMapping(value = "api/prijava")
public class PrijavljenOglasController {

	@Autowired
	private PrijavljenOglasService prijavljenOglasService;
	
	@Autowired
	private OglasService oglasService;
	
	@RequestMapping(value = "/add/{idOglas}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PrijavljeniOglasDTO> savePrijava(@PathVariable Long idOglas, @RequestBody PrijavljeniOglasDTO prijavljenOglasDTO) {
		
		Oglas oglas = oglasService.findOne(idOglas);
		
		PrijavljeniOglas prijavljen = new PrijavljeniOglas();
		prijavljen.setOpis(prijavljenOglasDTO.getOpis());
		prijavljen.setOglas(oglasService.findOne(idOglas));
		
		prijavljen = prijavljenOglasService.save(prijavljen);
		
		return new ResponseEntity<>(new PrijavljeniOglasDTO(prijavljen), HttpStatus.CREATED);
	}
}
