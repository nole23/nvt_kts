package com.konstrukcija.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Adresa {
	@Id
	@GeneratedValue
	private Long id;
	private String drzava;
	private String grad;
	private String ulica;
	private int broj;
	
	@OneToMany(mappedBy = "adresa", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Korisnik> korisnik = new HashSet<Korisnik>();
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "adresa", cascade = CascadeType.ALL)
	private Kompanija kompanija;
	
	public Kompanija getKompanija() {
		return kompanija;
	}
	public void setKompanija(Kompanija kompanija) {
		this.kompanija = kompanija;
	}
	public Set<Korisnik> getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Set<Korisnik> korisnik) {
		this.korisnik = korisnik;
	}
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
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	
	

}
