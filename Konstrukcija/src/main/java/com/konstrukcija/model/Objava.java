package com.konstrukcija.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Objava {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Korisnik korisnik;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Kompanija kompanija;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Nekretnina nekretnina;
	
	public Kompanija getKompanija() {
		return kompanija;
	}

	public void setKompanija(Kompanija kompanija) {
		this.kompanija = kompanija;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Nekretnina getNekretnina() {
		return nekretnina;
	}

	public void setNekretnina(Nekretnina nekretnina) {
		this.nekretnina = nekretnina;
	}
}
