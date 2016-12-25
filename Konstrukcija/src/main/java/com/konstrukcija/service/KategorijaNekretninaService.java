package com.konstrukcija.service;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.konstrukcija.model.KategorijaNekretnina;
import com.konstrukcija.repository.KategorijaNekretninaRepository;

@Service
public class KategorijaNekretninaService {

	@Autowired
	KategorijaNekretninaRepository kategorijaNekretninaService;
	
	public List<KategorijaNekretnina> findAll() {
		return kategorijaNekretninaService.findAll();
	}
}
