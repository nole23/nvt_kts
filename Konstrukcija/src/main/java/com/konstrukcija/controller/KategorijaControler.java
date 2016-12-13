package com.konstrukcija.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.KategorijaDTO;
import com.konstrukcija.model.Kategorija;
import com.konstrukcija.service.KategorijaService;

@RestController
@RequestMapping(value="api/kategorija")
public class KategorijaControler {

	@Autowired
	private KategorijaService kategirjaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KategorijaDTO>> getKategorija() {
		List<Kategorija> kategorija = kategirjaService.findAll();
		
		List<KategorijaDTO> kategorijaDTO = new ArrayList<>();
		for(Kategorija k : kategorija) {
			kategorijaDTO.add(new KategorijaDTO(k));
		}
		return new ResponseEntity<>(kategorijaDTO, HttpStatus.OK);
	}
}
