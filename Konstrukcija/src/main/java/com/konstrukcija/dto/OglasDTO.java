package com.konstrukcija.dto;

import com.konstrukcija.model.Oglas;

public class OglasDTO {

	private Long id;
	private String datum_objave;
	private String datum_azuriranja;
	private String datum_isteka;
	
	public OglasDTO() {}
	
	public OglasDTO(Long id, String datum_objave, String datum_azuriranja, String datum_isteka) {
		super();
		this.id = id;
		this.datum_objave = datum_objave;
		this.datum_azuriranja = datum_azuriranja;
		this.datum_isteka = datum_isteka;
	}
	
	public OglasDTO(Oglas oglas) {
		this.id = oglas.getId();
		this.datum_objave = oglas.getDatum_objave();
		this.datum_azuriranja = oglas.getDatum_azuriranja();
		this.datum_isteka = oglas.getDatum_isteka();
	}

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

	@Override
	public String toString() {
		return "OglasDTO [id=" + id + ", datum_objave=" + datum_objave
				+ ", datum_azuriranja=" + datum_azuriranja + ", datum_isteka="
				+ datum_isteka + "]";
	}
	
	
}
