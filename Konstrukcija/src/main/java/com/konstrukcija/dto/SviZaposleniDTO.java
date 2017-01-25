package com.konstrukcija.dto;

import com.konstrukcija.model.Zaposleni;

public class SviZaposleniDTO {
	
	private Long id;
	private KorisnikDTO korisnikDTO;
	private KompanijaDTO kompanijaDTO;
	private String uloga;
	
	public SviZaposleniDTO() {}
	
	public SviZaposleniDTO(Long id, KorisnikDTO korisnikDTO, KompanijaDTO kompanijaDTO, String uloga) {
		super();
		this.id = id;
		this.kompanijaDTO = kompanijaDTO;
		this.korisnikDTO = korisnikDTO;
		this.uloga = uloga;
	}
	
	public SviZaposleniDTO(Zaposleni zaposleni) {
		this.id = zaposleni.getId();
		this.uloga = zaposleni.getUloga();
		this.kompanijaDTO = new KompanijaDTO(zaposleni.getKompanija());
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
