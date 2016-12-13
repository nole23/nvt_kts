package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Objava;
import com.konstrukcija.repository.ObjavaRepository;

@Service
public class ObjavaService {

	@Autowired
	ObjavaRepository objavaRepository;
	
	public List<Objava> findAll() {
		return objavaRepository.findAll();
	}
}
