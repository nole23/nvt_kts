package com.konstrukcija.dto;

import com.konstrukcija.model.Adresa;
import com.konstrukcija.model.Korisnik;

public class KorisnikDTO {

	private String fname;
	private String lname;
	private String password;
	private String phone_number;
	private String email;
	private Adresa adresa;
	private String username;
	
	public KorisnikDTO() {}
	
	public KorisnikDTO(String fname, String lname, String password, String phone_number, String email, Adresa adresa, String username) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.phone_number = phone_number;
		this.email = email;
		this.adresa = adresa;
		this.username = username;
	}
	
	public KorisnikDTO(Korisnik korisnik) {
		this.fname = korisnik.getFname();
		this.lname = korisnik.getLname();
		this.password = korisnik.getPassword();
		this.phone_number = korisnik.getPhone_number();
		this.email = korisnik.getEmail();
		this.adresa = korisnik.getAdresa();
		this.username = korisnik.getUsername();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	@Override
	public String toString() {
		return "KorisnikDTO [fname=" + fname + ", lname="
				+ lname + ", pass=" + password + ", phone_number=" + phone_number
				+ ", email=" + email + ", adresa=" + adresa + "]";
	}
	
	
}
