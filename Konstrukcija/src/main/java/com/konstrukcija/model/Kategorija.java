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
	
	String name;
	
	@OneToMany(mappedBy = "kategorija", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<KategorijaNekretnina> kategorijaNekretnina = new HashSet<KategorijaNekretnina>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<KategorijaNekretnina> getKategorijaNekretnina() {
		return kategorijaNekretnina;
	}

	public void setKategorijaNekretnina(
			Set<KategorijaNekretnina> kategorijaNekretnina) {
		this.kategorijaNekretnina = kategorijaNekretnina;
	}
	
	
	
}
