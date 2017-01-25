package com.konstrukcija.dto;

import com.konstrukcija.model.Korisnik;

public class KorisniciListaDTO {

	private Long id;
	private String fname;
	private String lname;
	private String email;
	private String username;

	public KorisniciListaDTO() {
	}

	public KorisniciListaDTO(Long id, String fname, String lname, String email,
			String username) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.username = username;
	}

	public KorisniciListaDTO(Korisnik korisnik) {
		this.id = korisnik.getId();
		this.fname = korisnik.getFname();
		this.lname = korisnik.getLname();
		this.email = korisnik.getEmail();
		this.username = korisnik.getUsername();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
