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
public class Adresa {

	@Id
	@GeneratedValue
	private Long id;
	private String drzava;
	private String grad;
	private String ulica;
	private int broj_ulice;
	private String broj_zgrade;
	private int broj_stama;
	
	@OneToMany(mappedBy = "adresa", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Korisnik> korisnik = new HashSet<Korisnik>();
	
	@OneToMany(mappedBy = "adresa", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Kompanija> kompanija = new HashSet<Kompanija>();

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

	public int getBroj_stama() {
		return broj_stama;
	}

	public void setBroj_stama(int broj_stama) {
		this.broj_stama = broj_stama;
	}

	public Set<Korisnik> getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Set<Korisnik> korisnik) {
		this.korisnik = korisnik;
	}

	public Set<Kompanija> getKompanija() {
		return kompanija;
	}

	public void setKompanija(Set<Kompanija> kompanija) {
		this.kompanija = kompanija;
	}

	@Override
	public String toString() {
		return "Adresa [id=" + id + ", drzava=" + drzava + ", grad=" + grad
				+ ", ulica=" + ulica + ", broj_ulice=" + broj_ulice
				+ ", broj_zgrade=" + broj_zgrade + ", broj_stama=" + broj_stama
				+ ", korisnik=" + korisnik + ", kompanija=" + kompanija + "]";
	}
}
