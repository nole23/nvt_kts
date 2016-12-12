package com.konstrukcija.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kategorija {
	@Id
	@GeneratedValue
	private Long id;
	
	private String kategorija;//da li se prodaje ili iznajmljuje 
	private String vrsta_nekretnine;//da li je stan ili kuca
	
	@OneToMany(mappedBy = "kategorija", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<KategorijaNekretnine> kategorijaNekretnine = new HashSet<KategorijaNekretnine>();

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

	public Set<KategorijaNekretnine> getKategorijaNekretnine() {
		return kategorijaNekretnine;
	}

	public void setKategorijaNekretnine(
			Set<KategorijaNekretnine> kategorijaNekretnine) {
		this.kategorijaNekretnine = kategorijaNekretnine;
	}
	
	
}
