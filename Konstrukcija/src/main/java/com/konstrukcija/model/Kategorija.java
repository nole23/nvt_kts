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
	private String naziv;
	private String tip;
	
	@OneToMany(mappedBy = "kategorija", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Nekretnina> nekretnina = new HashSet<Nekretnina>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Set<Nekretnina> getNekretnina() {
		return nekretnina;
	}

	public void setNekretnina(Set<Nekretnina> nekretnina) {
		this.nekretnina = nekretnina;
	}

	
}
