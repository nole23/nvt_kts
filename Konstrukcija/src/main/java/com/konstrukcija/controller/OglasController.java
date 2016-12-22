package com.konstrukcija.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.OglasDTO;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.model.Oglasi;
import com.konstrukcija.repository.NekretninaRepository;
import com.konstrukcija.repository.OglasiRepository;
import com.konstrukcija.service.NekretnineService;
import com.konstrukcija.service.OglasService;

@RestController
@RequestMapping(value = "api/oglas")
public class OglasController {

	@Autowired
	private OglasService oglasService;
	
	@Autowired
	private NekretnineService nekretninaService;
	
	@Autowired
	private OglasiRepository oglasiRepository;
	
	@Autowired
	private NekretninaRepository nekretninaRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<OglasDTO>> getAllOglas() {
		List<Oglas> oglas = oglasService.fidnAll();
		
		List<OglasDTO> oglasDTO = new ArrayList<>();
		for(Oglas o : oglas) {
			oglasDTO.add(new OglasDTO(o));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//Dodati novi oglas
	@RequestMapping(value = "/add/{idNekretnina}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<OglasDTO> saveOglas(@PathVariable Long idNekretnina, @RequestBody OglasDTO oglasDTO) {
		
		Nekretnina nekretnina = nekretninaService.findOne(idNekretnina);
		if(nekretnina == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Oglas oglas = new Oglas();
		Oglasi oglasi = new Oglasi();
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		
		
		oglas.setDatum_objave(dateFormat.format(date));
		oglas.setDatum_azuriranja(oglasDTO.getDatum_azuriranja());
		oglas.setDatum_isteka(oglasDTO.getDatum_isteka());
		
		oglasi.setNekretnina(nekretninaRepository.findOne(idNekretnina));
		oglasi.setOglas(oglas);
		
		oglas = oglasService.save(oglas);
		oglasiRepository.save(oglasi);
		
		return new ResponseEntity<>(new OglasDTO(oglas), HttpStatus.CREATED);
	}
}
