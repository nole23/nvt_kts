package com.konstrukcija.dto;

import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.PrijavljeniOglas;



public class PrijavljeniOglasDTO {

	private Long id;
	private String opis;
	private OglasDTO oglas;
	private Korisnik korisnik;
	
	public PrijavljeniOglasDTO() {}

	public PrijavljeniOglasDTO(Long id, String opis, OglasDTO oglas, Korisnik korisnik) {
		super();
		this.id = id;
		this.opis = opis;
		this.oglas = oglas;
		this.korisnik = korisnik;
	}

	public PrijavljeniOglasDTO(PrijavljeniOglas prijavljen) {
		this.id = prijavljen.getId();
		this.opis = prijavljen.getOpis();
		this.oglas = new OglasDTO(prijavljen.getOglas());
		this.korisnik = prijavljen.getKorisnik();
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public OglasDTO getOglas() {
		return oglas;
	}

	public void setOglas(OglasDTO oglas) {
		this.oglas = oglas;
	}
	
	
}
