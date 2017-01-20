package com.konstrukcija.dto;

import java.sql.Blob;

import com.konstrukcija.model.Image;
import com.konstrukcija.model.Korisnik;

public class ImageDTO {

	private Long id;
	private Blob picture;
	private Korisnik korisnik;
	private NekretninaDTO nekretninaDTO;
	
	public ImageDTO() {}
	
	public ImageDTO(Long id, Blob picture, Korisnik korisnik, NekretninaDTO nekretninaDTO) {
		super();
		this.id = id;
		this.picture = picture;
		this.korisnik = korisnik;
		this.nekretninaDTO = nekretninaDTO;
	}
	
	public ImageDTO(Image image) {
		this.id = image.getId();
		this.picture = image.getPicture();
		this.korisnik = image.getKorisnik();
		this.nekretninaDTO = new NekretninaDTO(image.getNekretnina());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public NekretninaDTO getNekretninaDTO() {
		return nekretninaDTO;
	}

	public void setNekretninaDTO(NekretninaDTO nekretninaDTO) {
		this.nekretninaDTO = nekretninaDTO;
	}
}
