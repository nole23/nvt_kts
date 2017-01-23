package com.konstrukcija.dto;

import com.konstrukcija.model.Objavio;

public class ObjavioDTO {

	private Long id;
	private KorisnikDTO korisnikDTO;
	private KompanijaDTO kompanijaDTO;
	
	public ObjavioDTO() {}
	
	public ObjavioDTO(Long id, KorisnikDTO korisnikDTO, KompanijaDTO kompanijaDTO) {
		super();
		this.id = id;
		if(kompanijaDTO == null)
			this.korisnikDTO = korisnikDTO;
		if(korisnikDTO == null)
			this.kompanijaDTO = kompanijaDTO;
	}
	
	public ObjavioDTO(Objavio objavio) {
		this.id = objavio.getId();
		if(kompanijaDTO == null)
			this.korisnikDTO = new KorisnikDTO(objavio.getKorisnik());
		if(korisnikDTO == null)
			this.kompanijaDTO = new KompanijaDTO(objavio.getKompanija());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public KorisnikDTO getKorisnikDTO() {
		return korisnikDTO;
	}

	public void setKorisnikDTO(KorisnikDTO korisnikDTO) {
		this.korisnikDTO = korisnikDTO;
	}

	public KompanijaDTO getKompanijaDTO() {
		return kompanijaDTO;
	}

	public void setKompanijaDTO(KompanijaDTO kompanijaDTO) {
		this.kompanijaDTO = kompanijaDTO;
	}

}
