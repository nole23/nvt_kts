package com.konstrukcija.dto;

import com.konstrukcija.model.Korisnik;

public class KorisnikDTO {

	private Long id;
	private String fname;
	private String lname;
	private String pass;
	private String phone_number;
	private String email;
	
	public KorisnikDTO() {}
	
	public KorisnikDTO(Long id, String fname, String lname, String pass, String phone_number, String email) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.pass = pass;
		this.phone_number = phone_number;
		this.email = email;
	}
	
	public KorisnikDTO(Korisnik korisnik) {
		this.id = korisnik.getId();
		this.fname = korisnik.getFname();
		this.lname = korisnik.getLname();
		this.pass = korisnik.getPass();
		this.phone_number = korisnik.getPhone_number();
		this.email = korisnik.getEmail();
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "KorisnikDTO [id=" + id + ", fname=" + fname + ", lname="
				+ lname + ", pass=" + pass + ", phone_number=" + phone_number
				+ ", email=" + email + "]";
	}
	
	
}
