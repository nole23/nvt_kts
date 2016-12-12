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
public class Oglas {
	@Id
	@GeneratedValue
	private Long id;
	private String datum_objave;
	private String datum_azuriranja;
	private String datum_isteka;
	
	@OneToMany(mappedBy = "oglas", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Oglasi> oglasi = new HashSet<Oglasi>();
	
	@OneToMany(mappedBy = "oglas", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Komentari> komentari = new HashSet<Komentari>();
	
	@OneToMany(mappedBy = "oglas", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Ocene> ocene = new HashSet<Ocene>();

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

	public Set<Oglasi> getOglasi() {
		return oglasi;
	}

	public void setOglasi(Set<Oglasi> oglasi) {
		this.oglasi = oglasi;
	}

	public Set<Komentari> getKomentari() {
		return komentari;
	}

	public void setKomentari(Set<Komentari> komentari) {
		this.komentari = komentari;
	}

	public Set<Ocene> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocene> ocene) {
		this.ocene = ocene;
	}
}
