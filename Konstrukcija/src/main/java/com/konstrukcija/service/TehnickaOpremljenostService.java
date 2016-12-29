package com.konstrukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.TehnickaOpremljenost;
import com.konstrukcija.repository.TehnickaOpremljenostRepository;

@Service
public class TehnickaOpremljenostService {

	@Autowired
	TehnickaOpremljenostRepository tehnickaOpremljenostRepository;
	
	public List<TehnickaOpremljenost> findAll() {
		return tehnickaOpremljenostRepository.findAll();
	}
	
	public TehnickaOpremljenost save(TehnickaOpremljenost tehnickaOpremljenost) {
		return tehnickaOpremljenostRepository.save(tehnickaOpremljenost);
	}
	
	public TehnickaOpremljenost findOne(Long id) {
		return tehnickaOpremljenostRepository.findOne(id);
	}
	
	public void remove(Long id) {
		tehnickaOpremljenostRepository.delete(id);
	}
}
