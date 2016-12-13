package com.konstrukcija.dto;

import com.konstrukcija.model.Adresa;

public class AdresaDTO {

	private Long id;
	private String drzava;
	private String grad;
	private String ulica;
	private int broj;
	
	public AdresaDTO() {}
	
	public AdresaDTO(Long id, String drzava, String grad, String ulica, int broj) {
		super();
		this.id = id;
		this.drzava = drzava;
		this.grad = grad;
		this.ulica = ulica;
		this.broj = broj;
	}
	
	public AdresaDTO(Adresa adresa) {
		this.id = adresa.getId();
		this.drzava = adresa.getDrzava();
		this.grad = adresa.getGrad();
		this.ulica = adresa.getUlica();
		this.broj = adresa.getBroj();
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

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	@Override
	public String toString() {
		return "AdresaDTO [id=" + id + ", drzava=" + drzava + ", grad=" + grad
				+ ", ulica=" + ulica + ", broj=" + broj + "]";
	}
	
	
}
