package com.konstrukcija.dto;

import com.konstrukcija.model.Kompanija;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Objava;

public class ObjavaDTO {

	private Long id;
	private Korisnik korisnik;
	private Nekretnina nekretnina;
	private Kompanija kompanija;
	
	public ObjavaDTO() {}
	
	public ObjavaDTO(Long id, Korisnik korisnik, Nekretnina nekretnina, Kompanija kompanija) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.nekretnina = nekretnina;
		this.kompanija = kompanija;
	}
	
	public ObjavaDTO(Objava objava) {
		this.id = objava.getId();
		this.korisnik = objava.getKorisnik();
		this.nekretnina = objava.getNekretnina();
		this.kompanija = objava.getKompanija();
	}

	
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

	@Override
	public String toString() {
		return "ObjavaDTO [id=" + id + ", korisnik=" + korisnik
				+ ", nekretnina=" + nekretnina + "]";
	}
	
	
}
