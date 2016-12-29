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
	private int broj_ulice;
	private String broj_zgrade;
	private String broj_stana;
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

	public int getBroj_ulice() {
		return broj_ulice;
	}

	public void setBroj_ulice(int broj_ulice) {
		this.broj_ulice = broj_ulice;
	}

	public String getBroj_zgrade() {
		return broj_zgrade;
	}

	public void setBroj_zgrade(String broj_zgrade) {
		this.broj_zgrade = broj_zgrade;
	}

	public String getBroj_stana() {
		return broj_stana;
	}

	public void setBroj_stana(String broj_stana) {
		this.broj_stana = broj_stana;
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

	@Override
	public String toString() {
		return "Lokacija [id=" + id + ", drzava=" + drzava + ", grad=" + grad
				+ ", oblas=" + oblas + ", ulica=" + ulica + ", broj_ulice="
				+ broj_ulice + ", broj_zgrade=" + broj_zgrade + ", broj_stana="
				+ broj_stana + ", geo_duzina=" + geo_duzina + ", geo_sirina="
				+ geo_sirina + ", nekretnina=" + nekretnina + "]";
	}
}
