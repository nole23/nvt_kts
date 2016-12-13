package com.konstrukcija.dto;

import com.konstrukcija.model.Kategorija;

public class KategorijaDTO {

	private Long id;
	private String kategorija;
	private String vrsta_nekretnine;
	
	public KategorijaDTO() {}
	
	public KategorijaDTO(Long id, String kategorija, String vrsta_nekretnine) {
		super();
		this.id = id;
		this.kategorija = kategorija;
		this.vrsta_nekretnine = vrsta_nekretnine;
	}
	
	public KategorijaDTO(Kategorija kategorija) {
		this.id = kategorija.getId();
		this.kategorija = kategorija.getKategorija();
		this.vrsta_nekretnine = kategorija.getVrsta_nekretnine();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public String getVrsta_nekretnine() {
		return vrsta_nekretnine;
	}

	public void setVrsta_nekretnine(String vrsta_nekretnine) {
		this.vrsta_nekretnine = vrsta_nekretnine;
	}

	@Override
	public String toString() {
		return "KategorijaDTO [id=" + id + ", kategorija=" + kategorija
				+ ", vrsta_nekretnine=" + vrsta_nekretnine + "]";
	}
	
	
}
