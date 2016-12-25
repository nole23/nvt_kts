package com.konstrukcija.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class KategorijaNekretnina {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Kategorija kategorija;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Nekretnina nekretnina;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public Nekretnina getNekretnina() {
		return nekretnina;
	}

	public void setNekretnina(Nekretnina nekretnina) {
		this.nekretnina = nekretnina;
	}

	
}
