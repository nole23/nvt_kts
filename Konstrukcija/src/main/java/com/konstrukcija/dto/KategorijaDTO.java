package com.konstrukcija.dto;

import com.konstrukcija.model.Kategorija;

public class KategorijaDTO {

	private Long id;
	private String name;
	private String tip;
	
	public KategorijaDTO() {}
	
	public KategorijaDTO(Long id, String name, String tip) {
		super();
		this.id = id;
		this.name = name;
		this.tip = tip;
	}
	
	public KategorijaDTO(Kategorija kategorija) {
		this.id = kategorija.getId();
		this.name = kategorija.getName();
		this.tip = kategorija.getTip();
	}

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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "KategorijaDTO [id=" + id + ", naziv=" + name + ", tip=" + tip
				+ "]";
	}
}
