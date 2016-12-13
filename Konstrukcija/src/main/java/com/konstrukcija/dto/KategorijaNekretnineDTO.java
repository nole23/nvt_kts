package com.konstrukcija.dto;

import com.konstrukcija.model.Kategorija;
import com.konstrukcija.model.KategorijaNekretnine;
import com.konstrukcija.model.Nekretnina;

public class KategorijaNekretnineDTO {
	
	private Long id;
	private Kategorija kategorija; 
	private Nekretnina nekretnina;
	
	public KategorijaNekretnineDTO() {}
	
	public KategorijaNekretnineDTO(Long id, Kategorija kategorija, Nekretnina nekretnina) {
		super();
		this.id = id;
		this.kategorija = kategorija;
		this.nekretnina = nekretnina;
	}
	
	public KategorijaNekretnineDTO(KategorijaNekretnine kategorijaNekretnine) {
		this.id = kategorijaNekretnine.getId();
		this.kategorija = kategorijaNekretnine.getKategorija();
		this.nekretnina = kategorijaNekretnine.getNekretnina();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public Nekretnina getNekretnina() {
		return nekretnina;
	}

	public void setNekretnina(Nekretnina nekretnina) {
		this.nekretnina = nekretnina;
	}

	@Override
	public String toString() {
		return "KategorijaNekretnineDTO [id=" + id + ", kategorija="
				+ kategorija + ", nekretnina=" + nekretnina + "]";
	}
	
	
}
