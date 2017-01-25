package com.konstrukcija.dto;

import com.konstrukcija.model.Oglas;
import com.konstrukcija.model.PrijavaOglasa;

public class PrijavaOglasaDTO {

	private Long id;
	private boolean prijava;
	private OglasDTO oglasDTO;
	
	public PrijavaOglasaDTO() {}

	public PrijavaOglasaDTO(Long id, boolean prijava, OglasDTO oglasDTO) {
		super();
		this.id = id;
		this.prijava = prijava;
		this.oglasDTO = oglasDTO;
	}

	public PrijavaOglasaDTO(PrijavaOglasa prijavljen) {
		this.id = prijavljen.getId();
		this.prijava = prijavljen.isPrijava();
		this.oglasDTO = new OglasDTO(prijavljen.getOglas());
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

	public OglasDTO getOglasDTO() {
		return oglasDTO;
	}

	public void setOglasDTO(OglasDTO oglasDTO) {
		this.oglasDTO = oglasDTO;
	}
	
}
