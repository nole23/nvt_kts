package com.konstrukcija.dto;

import com.konstrukcija.model.Komentari;
import com.konstrukcija.model.Oglas;

public class KomentarDTO {
	
	private Long id;
	private String komentar;
	private Oglas oglas;
	
	public KomentarDTO() {}
	
	public KomentarDTO(Long id, String komentar, Oglas oglas) {
		super();
		this.id = id; 
		this.komentar = komentar;
		this.oglas = oglas;
	}
	
	public KomentarDTO(Komentari komentar) {
		this.id = komentar.getId();
		this.komentar = komentar.getKomentar();
		this.oglas = komentar.getOglas();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}
}
