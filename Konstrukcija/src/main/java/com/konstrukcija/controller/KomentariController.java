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

import com.konstrukcija.dto.KomentarDTO;
import com.konstrukcija.model.Komentari;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.service.KomentariService;
import com.konstrukcija.service.OglasService;

@RestController
@RequestMapping(value = "api/komentari")
public class KomentariController {

	@Autowired
	private KomentariService komentariService;
	
	@Autowired
	OglasService oglasService;
	
	//Pregled svih komentara
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<KomentarDTO>> getfindAll() {
		List<Komentari> komentari = komentariService.findALl();
		
		List<KomentarDTO> komentariDTO = new ArrayList<>();
		for(Komentari k : komentari) {
			komentariDTO.add(new KomentarDTO(k));
		}
		return new ResponseEntity<>(komentariDTO, HttpStatus.OK);
	}
	
	//Pretraga komentara po idOglasa
	@RequestMapping(value = "/komentarii", method = RequestMethod.GET)
	public ResponseEntity<List<KomentarDTO>> getKomentarByOglas(@RequestBody String oglasi) {
		
		Oglas oglas = oglasService.findOne(Long.parseLong(oglasi));
		
		Set<Komentari> komentari = oglas.getKomentari();
		List<KomentarDTO> komentarDTO = new ArrayList<>();
		for(Komentari k : komentari) {
			KomentarDTO komentariDTO = new KomentarDTO();
			
			komentariDTO.setId(k.getId());
			komentariDTO.setKomentar(k.getKomentar());
			komentariDTO.setOglas(k.getOglas());
			
			komentarDTO.add(komentariDTO);
		}
		return new ResponseEntity<>(komentarDTO, HttpStatus.OK);
	}
	
	//Dodavanje novog komentara
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<KomentarDTO> saveKomentar(@RequestBody KomentarDTO komentarDTO) {
		Komentari komentari = new Komentari();
		
		komentari.setKomentar(komentarDTO.getKomentar());
		komentari.setOglas(komentarDTO.getOglas());
		
		komentari = komentariService.save(komentari);
		return new ResponseEntity<>(new KomentarDTO(komentari), HttpStatus.CREATED);
	}
}
