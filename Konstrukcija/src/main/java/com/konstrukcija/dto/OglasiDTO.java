package com.konstrukcija.dto;

import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.model.Oglasi;

public class OglasiDTO {

	private Oglas oglas;
	private Nekretnina nekretnina;
	
	public OglasiDTO() {}
	
	public OglasiDTO(Oglas oglas, Nekretnina nekretnina) {
		super();
		this.oglas = oglas;
		this.nekretnina = nekretnina;
	}
	
	public OglasiDTO(Oglasi oglasi) {
		this.oglas = oglasi.getOglas();
		this.nekretnina = oglasi.getNekretnina();
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
