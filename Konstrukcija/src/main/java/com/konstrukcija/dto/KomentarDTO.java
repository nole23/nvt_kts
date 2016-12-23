package com.konstrukcija.dto;

import com.konstrukcija.model.Komentari;

public class KomentarDTO {
	
	private Long id;
	private String komentar;
	private OglasDTO oglas;
	
	public KomentarDTO() {}
	
	public KomentarDTO(Long id, String komentar, OglasDTO oglas) {
		super();
		this.id = id; 
		this.komentar = komentar;
		this.oglas = oglas;
	}
	
	public KomentarDTO(Komentari komentar) {
		this.id = komentar.getId();
		this.komentar = komentar.getKomentar();
		this.oglas = new OglasDTO(komentar.getOglas());
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

	public OglasDTO getOglas() {
		return oglas;
	}

	public void setOglas(OglasDTO oglas) {
		this.oglas = oglas;
	}
}
