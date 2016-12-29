package com.konstrukcija.dto;

import com.konstrukcija.model.Adresa;

public class AdresaDTO {

	private Long id;
	private String drzava;
	private String grad;
	private String ulica;
	private int broj_ulice;
	private String broj_zgrade;
	private int broj_stama;
	
	public AdresaDTO() {}
	
	public AdresaDTO(Long id, String drzava, String grad, String ulica, 
			int broj_ulice, String broj_zgrade, int broj_stama) {
		super();
		this.id = id;
		this.drzava = drzava;
		this.grad = grad;
		this.ulica = ulica;
		this.broj_ulice = broj_ulice;
		this.broj_zgrade = broj_zgrade;
		this.broj_stama = broj_stama;
	}
	
	public AdresaDTO(Adresa adresa) {
		this.id = adresa.getId();
		this.drzava = adresa.getDrzava();
		this.grad = adresa.getGrad();
		this.ulica = adresa.getUlica();
		this.broj_ulice = adresa.getBroj_ulice();
		this.broj_zgrade = adresa.getBroj_zgrade();
		this.broj_stama = adresa.getBroj_stama();
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

	public int getBroj_stama() {
		return broj_stama;
	}

	public void setBroj_stama(int broj_stama) {
		this.broj_stama = broj_stama;
	}

	@Override
	public String toString() {
		return "AdresaDTO [id=" + id + ", drzava=" + drzava + ", grad=" + grad
				+ ", ulica=" + ulica + ", broj_ulice=" + broj_ulice
				+ ", broj_zgrade=" + broj_zgrade + ", broj_stama=" + broj_stama
				+ "]";
	}
}
