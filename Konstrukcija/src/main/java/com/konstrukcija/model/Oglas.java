package com.konstrukcija.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Oglas {

	@Id
	@GeneratedValue
	private Long id;
	private String datum_objave;
	private String datum_azuriranja;
	private String datum_isteka;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Nekretnina nekretnina;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Objavio objavio;
	
	@OneToMany(mappedBy = "oglas", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Ocena> ocena = new HashSet<Ocena>();
	
	@OneToMany(mappedBy = "oglas", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Komentar> komentar = new HashSet<Komentar>();
	
	@OneToMany(mappedBy = "oglas", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<PrijavaOglasa> prijavaOglasa = new HashSet<PrijavaOglasa>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatum_objave() {
		return datum_objave;
	}

	public void setDatum_objave(String datum_objave) {
		this.datum_objave = datum_objave;
	}

	public String getDatum_azuriranja() {
		return datum_azuriranja;
	}

	public void setDatum_azuriranja(String datum_azuriranja) {
		this.datum_azuriranja = datum_azuriranja;
	}

	public String getDatum_isteka() {
		return datum_isteka;
	}

	public void setDatum_isteka(String datum_isteka) {
		this.datum_isteka = datum_isteka;
	}

	public Nekretnina getNekretnina() {
		return nekretnina;
	}

	public void setNekretnina(Nekretnina nekretnina) {
		this.nekretnina = nekretnina;
	}

	public Objavio getObjavio() {
		return objavio;
	}

	public void setObjavio(Objavio objavio) {
		this.objavio = objavio;
	}

	public Set<Ocena> getOcena() {
		return ocena;
	}

	public void setOcena(Set<Ocena> ocena) {
		this.ocena = ocena;
	}

	public Set<Komentar> getKomentar() {
		return komentar;
	}

	public void setKomentar(Set<Komentar> komentar) {
		this.komentar = komentar;
	}

	public Set<PrijavaOglasa> getPrijavaOglasa() {
		return prijavaOglasa;
	}

	public void setPrijavaOglasa(Set<PrijavaOglasa> prijavaOglasa) {
		this.prijavaOglasa = prijavaOglasa;
	}
}
