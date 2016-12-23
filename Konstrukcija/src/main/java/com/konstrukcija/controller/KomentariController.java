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
import com.konstrukcija.dto.OglasDTO;
import com.konstrukcija.model.Komentari;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.repository.OglasRepository;
import com.konstrukcija.service.KomentariService;
import com.konstrukcija.service.OglasService;

@RestController
@RequestMapping(value = "api/komentari")
public class KomentariController {

	@Autowired
	private KomentariService komentarService;
	
	@Autowired
	private OglasService oglasService;
	
	
	@RequestMapping(value = "/add/{idOglas}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<KomentarDTO> saveKomentar(@PathVariable Long idOglas, @RequestBody KomentarDTO komentarDTO) {
		
		Oglas oglas = oglasService.findOne(idOglas);
		if(oglas == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
		}
		
		Komentari komentar = new Komentari();
		komentar.setKomentar(komentarDTO.getKomentar());
		komentar.setOglas(oglasService.findOne(idOglas));
		
		komentar = komentarService.save(komentar);
		
		return new ResponseEntity<>(new KomentarDTO(komentar), HttpStatus.CREATED);
	}
	
		
		
		
}
