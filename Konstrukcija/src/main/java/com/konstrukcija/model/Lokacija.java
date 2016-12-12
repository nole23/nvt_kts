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
public class Lokacija {
	@Id
	@GeneratedValue
	private Long id;
	private String drzava;
	private String grad;
	private String oblas;
	private String ulica;
	private int brojPTT;
	private String brStana;
	private String geo_duzina;
	private String geo_sirina;
	
	@OneToMany(mappedBy = "lokacija", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Nekretnina> nekretnina = new HashSet<Nekretnina>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getOblas() {
		return oblas;
	}

	public void setOblas(String oblas) {
		this.oblas = oblas;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getBrojPTT() {
		return brojPTT;
	}

	public void setBrojPTT(int brojPTT) {
		this.brojPTT = brojPTT;
	}

	public String getBrStana() {
		return brStana;
	}

	public void setBrStana(String brStana) {
		this.brStana = brStana;
	}

	public String getGeo_duzina() {
		return geo_duzina;
	}

	public void setGeo_duzina(String geo_duzina) {
		this.geo_duzina = geo_duzina;
	}

	public String getGeo_sirina() {
		return geo_sirina;
	}

	public void setGeo_sirina(String geo_sirina) {
		this.geo_sirina = geo_sirina;
	}

	public Set<Nekretnina> getNekretnina() {
		return nekretnina;
	}

	public void setNekretnina(Set<Nekretnina> nekretnina) {
		this.nekretnina = nekretnina;
	}
}
