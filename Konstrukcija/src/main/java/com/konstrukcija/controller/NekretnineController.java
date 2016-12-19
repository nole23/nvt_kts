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
import com.konstrukcija.service.NekretnineService;

@RestController
@RequestMapping(value = "api/estate")
public class NekretnineController {
	
	@Autowired
	private NekretnineService nekretninaService;
	
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
	
	//Cuvanje nove nekretnine
	@RequestMapping(value = "/{vrsta}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> saveNekretnina(@PathVariable String vrsta, @RequestBody NekretninaDTO nekretninaDTO) {
		Nekretnina nekretnina = new Nekretnina();
		
		if(vrsta.equals("kuca") || vrsta.equals("vikendica")) {
			nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
			nekretnina.setCena(nekretninaDTO.getCena());
			nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
			nekretnina.setSobnost(nekretninaDTO.getSobnost());
			nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
			nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
			nekretnina.setSpratova(nekretninaDTO.getSpratova());
			nekretnina.setSprat(null);
			nekretnina.setOpis(nekretninaDTO.getOpis());
			nekretnina.setLokacija(null);
			nekretnina.setTehnickaOpremljenost(null);
			
			nekretnina = nekretninaService.save(nekretnina);
			
			
		} else if(vrsta.equals("Stan")) {
			nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
			nekretnina.setCena(nekretninaDTO.getCena());
			nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
			nekretnina.setSobnost(nekretninaDTO.getSobnost());
			nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
			nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
			nekretnina.setSpratova(null);
			nekretnina.setSprat(nekretninaDTO.getSprat());
			nekretnina.setOpis(nekretninaDTO.getOpis());
			nekretnina.setLokacija(null);
			nekretnina.setTehnickaOpremljenost(null);
			
			nekretnina = nekretninaService.save(nekretnina);
			
			
		} else if(vrsta.equals("garaza")) {
			nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
			nekretnina.setCena(nekretninaDTO.getCena());
			nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
			nekretnina.setSobnost(null);
			nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
			nekretnina.setGrejanje(null);
			nekretnina.setSpratova(null);
			nekretnina.setSprat(null);
			nekretnina.setOpis(nekretninaDTO.getOpis());
			nekretnina.setLokacija(null);
			nekretnina.setTehnickaOpremljenost(null);
			
			nekretnina = nekretninaService.save(nekretnina);
			
			
		} else if(vrsta.equals("plac")) {
			nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
			nekretnina.setCena(nekretninaDTO.getCena());
			nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
			nekretnina.setSobnost(null);
			nekretnina.setStanje_objekta(null);
			nekretnina.setGrejanje(null);
			nekretnina.setSpratova(null);
			nekretnina.setSprat(null);
			nekretnina.setOpis(nekretninaDTO.getOpis());
			nekretnina.setLokacija(null);
			nekretnina.setTehnickaOpremljenost(null);
			
			nekretnina = nekretninaService.save(nekretnina);
			
		} else if(vrsta.equals("lokal")) {
			
			
			nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
			nekretnina.setCena(nekretninaDTO.getCena());
			nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
			nekretnina.setSobnost(null);
			nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
			nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
			nekretnina.setSpratova(null);
			nekretnina.setSprat(nekretninaDTO.getSprat());
			nekretnina.setOpis(nekretninaDTO.getOpis());
			nekretnina.setLokacija(null);
			nekretnina.setTehnickaOpremljenost(null);
			
			nekretnina = nekretninaService.save(nekretnina);
			
		}
		
		return new ResponseEntity<>("Uspesno ste dodali "+vrsta, HttpStatus.CREATED);
		
		
	}

}
