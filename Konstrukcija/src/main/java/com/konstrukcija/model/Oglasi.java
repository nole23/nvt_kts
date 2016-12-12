package com.konstrukcija.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Oglasi {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Oglas oglas;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Nekretnina nekretnina;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public Nekretnina getNekretnina() {
		return nekretnina;
	}

	public void setNekretnina(Nekretnina nekretnina) {
		this.nekretnina = nekretnina;
	}
	
	
}
