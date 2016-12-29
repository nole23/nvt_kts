package com.konstrukcija.dto;

import com.konstrukcija.model.Oglas;
import com.konstrukcija.model.PrijavaOglasa;

public class PrijavaOglasaDTO {

	private Long id;
	private boolean prijava;
	private Oglas oglas;
	
	public PrijavaOglasaDTO() {}

	public PrijavaOglasaDTO(Long id, boolean prijava, Oglas oglas) {
		super();
		this.id = id;
		this.prijava = prijava;
		this.oglas = oglas;
	}

	public PrijavaOglasaDTO(PrijavaOglasa prijavljen) {
		this.id = prijavljen.getId();
		this.prijava = prijavljen.isPrijava();
		this.oglas = prijavljen.getOglas();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPrijava() {
		return prijava;
	}

	public void setPrijava(boolean prijava) {
		this.prijava = prijava;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	@Override
	public String toString() {
		return "PrijavaOglasaDTO [id=" + id + ", prijava=" + prijava
				+ ", oglas=" + oglas + "]";
	}
	
}
