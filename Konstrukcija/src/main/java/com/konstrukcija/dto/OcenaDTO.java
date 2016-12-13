package com.konstrukcija.dto;

import com.konstrukcija.model.Ocene;
import com.konstrukcija.model.Oglas;

public class OcenaDTO {

	private Long id;
	private int ocena;
	private Oglas oglas;
	
	public OcenaDTO() {}
	
	public OcenaDTO(Long id, int ocena, Oglas oglas) {
		super();
		this.id = id;
		this.ocena = ocena;
		this.oglas = oglas;
	}
	

	public OcenaDTO(Ocene ocena) {
		this.id = ocena.getId();
		this.ocena = ocena.getOcena();
		this.oglas = ocena.getOglas();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	@Override
	public String toString() {
		return "OcenaDTO [id=" + id + ", ocena=" + ocena + ", oglas=" + oglas
				+ "]";
	}
	
	
}
