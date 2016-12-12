package com.konstrukcija.dto;

import com.konstrukcija.model.Lokacija;

public class LokacijaDTO {

	private Long id;
	private String drzava;
	private String grad;
	private String oblas;
	private String ulica;
	private int brojPTT;
	private String brStana;
	private String geo_duzina;
	private String geo_sirina;
	
	public LokacijaDTO() {}
	
	public LokacijaDTO(Lokacija lokacija) {
		this.id = lokacija.getId();
		this.drzava = lokacija.getDrzava();
		this.grad = lokacija.getGrad();
		this.oblas = lokacija.getOblas();
		this.ulica = lokacija.getUlica();
		this.brojPTT = lokacija.getBrojPTT();
		this.brStana = lokacija.getBrStana();
		this.geo_duzina = lokacija.getGeo_duzina();
		this.geo_sirina = lokacija.getGeo_sirina();
	}
	
	public LokacijaDTO(Long id, String drzava, String grad, String oblas, String ulica, int brojPTT, String brStana, String geo_duzina, String geo_sirina) {
		this.id = id;
		this.drzava = drzava;
		this.grad = grad;
		this.oblas = oblas;
		this.ulica = ulica;
		this.brojPTT = brojPTT;
		this.brStana = brStana;
		this.geo_duzina = geo_duzina;
		this.geo_sirina = geo_sirina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getOblas() {
		return oblas;
	}

	public void setOblas(String oblas) {
		this.oblas = oblas;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getBrojPTT() {
		return brojPTT;
	}

	public void setBrojPTT(int brojPTT) {
		this.brojPTT = brojPTT;
	}

	public String getBrStana() {
		return brStana;
	}

	public void setBrStana(String brStana) {
		this.brStana = brStana;
	}

	public String getGeo_duzina() {
		return geo_duzina;
	}

	public void setGeo_duzina(String geo_duzina) {
		this.geo_duzina = geo_duzina;
	}

	public String getGeo_sirina() {
		return geo_sirina;
	}

	public void setGeo_sirina(String geo_sirina) {
		this.geo_sirina = geo_sirina;
	}

	@Override
	public String toString() {
		return "LokacijaDTO [id=" + id + ", drzava=" + drzava + ", grad="
				+ grad + ", oblas=" + oblas + ", ulica=" + ulica + ", brojPTT="
				+ brojPTT + ", brStana=" + brStana + ", geo_duzina="
				+ geo_duzina + ", geo_sirina=" + geo_sirina + "]";
	}
	
	
	
}
