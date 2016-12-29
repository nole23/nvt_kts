package com.konstrukcija.dto;

import com.konstrukcija.model.Komentar;

public class KomentarDTO {

	private Long id;
	private String komentar;
	private OglasDTO oglasDTO;
	
public KomentarDTO() {}
	
	public KomentarDTO(Long id, String komentar, OglasDTO oglasDTO) {
		super();
		this.id = id; 
		this.komentar = komentar;
		this.oglasDTO = oglasDTO;
	}
	
	public KomentarDTO(Komentar komentar) {
		this.id = komentar.getId();
		this.komentar = komentar.getKomentar();
		this.oglasDTO = new OglasDTO(komentar.getOglas());
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

	public OglasDTO getOglasDTO() {
		return oglasDTO;
	}

	public void setOglasDTO(OglasDTO oglasDTO) {
		this.oglasDTO = oglasDTO;
	}

	@Override
	public String toString() {
		return "KomentarDTO [id=" + id + ", komentar=" + komentar
				+ ", oglasDTO=" + oglasDTO + "]";
	}
}
