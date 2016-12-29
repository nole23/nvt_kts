package com.konstrukcija.dto;

import com.konstrukcija.model.Lokacija;

public class LokacijaDTO {

	private Long id;
	private String drzava;
	private String grad;
	private String oblas;
	private String ulica;
	private int broj_ulice;
	private String broj_zgrade;
	private String broj_stana;
	private String geo_duzina;
	private String geo_sirina;
	
public LokacijaDTO() {}
	
	public LokacijaDTO(Lokacija lokacija) {
		this.id = lokacija.getId();
		this.drzava = lokacija.getDrzava();
		this.grad = lokacija.getGrad();
		this.oblas = lokacija.getOblas();
		this.ulica = lokacija.getUlica();
		this.broj_ulice = lokacija.getBroj_ulice();
		this.broj_zgrade = lokacija.getBroj_zgrade();
		this.broj_stana = lokacija.getBroj_stana();
		this.geo_duzina = lokacija.getGeo_duzina();
		this.geo_sirina = lokacija.getGeo_sirina();
	}
	
	public LokacijaDTO(Long id, String drzava, String grad, 
			String oblas, String ulica, int broj_ulice, String broj_zgrade,
			String broj_stana, String geo_duzina, String geo_sirina) {
		this.id = id;
		this.drzava = drzava;
		this.grad = grad;
		this.oblas = oblas;
		this.ulica = ulica;
		this.broj_ulice = broj_ulice;
		this.broj_zgrade = broj_zgrade;
		this.broj_stana = broj_stana;
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

	public int getBroj_ulice() {
		return broj_ulice;
	}

	public void setBroj_ulice(int broj_ulice) {
		this.broj_ulice = broj_ulice;
	}

	public String getBroj_zgrade() {
		return broj_zgrade;
	}

	public void setBroj_zgrade(String broj_zgrade) {
		this.broj_zgrade = broj_zgrade;
	}

	public String getBroj_stana() {
		return broj_stana;
	}

	public void setBroj_stana(String broj_stana) {
		this.broj_stana = broj_stana;
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
				+ grad + ", oblas=" + oblas + ", ulica=" + ulica
				+ ", broj_ulice=" + broj_ulice + ", broj_zgrade=" + broj_zgrade
				+ ", broj_stana=" + broj_stana + ", geo_duzina=" + geo_duzina
				+ ", geo_sirina=" + geo_sirina + "]";
	}
}
