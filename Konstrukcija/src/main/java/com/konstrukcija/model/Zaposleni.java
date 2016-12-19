package com.konstrukcija.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Zaposleni {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Korisnik korisnik;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Kompanija kompanija;
	
	
}
