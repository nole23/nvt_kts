package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.KategorijaDTO;
import com.konstrukcija.dto.NekretninaDTO;
import com.konstrukcija.model.Kategorija;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.service.KategorijaService;
import com.konstrukcija.service.NekretnineService;

@RestController
@RequestMapping(value="api/kategorija")
public class KategorijaController {

	@Autowired
	private KategorijaService kategorijaService;
	
	@Autowired
	private NekretnineService nekretninaService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<KategorijaDTO>> getKategorija() {
		List<Kategorija> kategorija = kategorijaService.findAll();
		
		List<KategorijaDTO> kategorijaDTO = new ArrayList<>();
		for(Kategorija k : kategorija) {
			kategorijaDTO.add(new KategorijaDTO(k));
		}
		return new ResponseEntity<>(kategorijaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nekretnina/{dKategorija}", method = RequestMethod.GET)
	public ResponseEntity<List<NekretninaDTO>> getNekretninaByIdKategorija(@PathVariable Long dKategorija) {
		
		Kategorija kategorija = kategorijaService.findOne(dKategorija);
		Set<Nekretnina> nekretnina = kategorija.getNekretnina();
		
		List<NekretninaDTO> nekretnineDTO = new ArrayList<>();
		for(Nekretnina n: nekretnina) {
			NekretninaDTO nekretninaDTO = new NekretninaDTO();
			nekretninaDTO.setId(n.getId());
			nekretninaDTO.setNaziv_nekretnine(n.getNaziv_nekretnine());
			nekretninaDTO.setCena(n.getCena());
			
			nekretnineDTO.add(nekretninaDTO);
			
		}
		
		return new ResponseEntity<>(nekretnineDTO ,HttpStatus.OK);
	}
	
}
