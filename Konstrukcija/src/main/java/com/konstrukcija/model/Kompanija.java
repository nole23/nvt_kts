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
public class Kompanija {

	@Id
	@GeneratedValue
	private Long id;
	private String nazivKomapnije;
	private String link;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Adresa adresa;
	
	@OneToMany(mappedBy = "kompanija", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Objavio> Objavio = new HashSet<Objavio>();
	
	@OneToMany(mappedBy = "kompanija", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Zaposleni> zaposleni = new HashSet<Zaposleni>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivKomapnije() {
		return nazivKomapnije;
	}

	public void setNazivKomapnije(String nazivKomapnije) {
		this.nazivKomapnije = nazivKomapnije;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public Set<Objavio> getObjavio() {
		return Objavio;
	}

	public void setObjavio(Set<Objavio> objavio) {
		Objavio = objavio;
	}

	public Set<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Set<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}
}
