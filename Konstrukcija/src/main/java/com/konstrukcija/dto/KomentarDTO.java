package com.konstrukcija.dto;

import com.konstrukcija.model.Komentar;
import com.konstrukcija.model.Korisnik;

public class KomentarDTO {

	private Long id;
	private String komentar;
	
public KomentarDTO() {}
	
	public KomentarDTO(Long id, String komentar) {
		super();
		this.id = id; 
		this.komentar = komentar;
		
	}
	
	public KomentarDTO(Komentar komentar) {
		this.id = komentar.getId();
		this.komentar = komentar.getKomentar();
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
}
