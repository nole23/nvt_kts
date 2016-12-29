package com.konstrukcija.dto;

import com.konstrukcija.model.Ocena;

public class OcenaDTO {

	private Long id;
	private int ocena;
	
	public OcenaDTO() {}
	
	public OcenaDTO(Long id, int ocena) {
		super();
		this.id = id;
		this.ocena = ocena;
	}
	

	public OcenaDTO(Ocena ocena) {
		this.id = ocena.getId();
		this.ocena = ocena.getOcena();
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
}
