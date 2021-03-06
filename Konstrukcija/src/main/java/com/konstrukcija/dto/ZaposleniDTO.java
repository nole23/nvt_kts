package com.konstrukcija.dto;

import com.konstrukcija.model.Zaposleni;

public class ZaposleniDTO {

	private Long id;
	private KorisnikDTO korisnikDTO;
	private KompanijaDTO kompanijaDTO;
	private String uloga;
	
	public ZaposleniDTO() {}
	
	public ZaposleniDTO(Long id, KorisnikDTO korisnikDTO, KompanijaDTO kompanijaDTO, String uloga) {
		super();
		this.id = id;
		this.kompanijaDTO = kompanijaDTO;
		this.korisnikDTO = korisnikDTO;
		this.uloga = uloga;
	}
	
	public ZaposleniDTO(Zaposleni zaposleni) {
		this.id = zaposleni.getId();
		this.uloga = zaposleni.getUloga();
		if(zaposleni.getKompanija() != null)
			this.kompanijaDTO = new KompanijaDTO(zaposleni.getKompanija());
		if(zaposleni.getKorisnik() == null)
			this.korisnikDTO = new KorisnikDTO(zaposleni.getKorisnik());
		
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

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
}
