package com.konstrukcija.dto;

import com.konstrukcija.model.Kategorija;

public class KategorijaDTO {

	private Long id;
	private String naziv;
	private String tip;
	
	public KategorijaDTO() {}
	
	public KategorijaDTO(Long id, String naziv, String tip) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
	}
	
	public KategorijaDTO(Kategorija kategorija) {
		this.id = kategorija.getId();
		this.naziv = kategorija.getNaziv();
		this.tip = kategorija.getTip();
	}

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

	@Override
	public String toString() {
		return "KategorijaDTO [id=" + id + ", naziv=" + naziv + ", tip=" + tip
				+ "]";
	}
}
