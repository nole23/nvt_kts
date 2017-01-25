package com.konstrukcija.dto;

import java.util.HashSet;
import java.util.Set;

import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Zaposleni;

public class KorisnikDTO {

	private String fname;
	private String lname;
	private String password;
	private String email;
	private String username;
	private AdresaDTO adresaDTO;
	private Set<ZaposleniDTO> zaposleniDTO;
	
	public KorisnikDTO() {}
	
	public KorisnikDTO(String fname, String lname, String password, String email, String username, AdresaDTO adresaDTO, Set<ZaposleniDTO> zaposleniDTO) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.email = email;
		this.username = username;
		this.adresaDTO = adresaDTO;
		this.zaposleniDTO = zaposleniDTO;
	}
	
	public KorisnikDTO(Korisnik korisnik) {
		this.fname = korisnik.getFname();
		this.lname = korisnik.getLname();
		this.password = korisnik.getPassword();
		this.email = korisnik.getEmail();
		this.username = korisnik.getUsername();
		if(korisnik.getAdresa() != null)
			this.adresaDTO = new AdresaDTO(korisnik.getAdresa());
		if(korisnik.getZaposleni() != null) {
			this.zaposleniDTO = new HashSet<ZaposleniDTO>();
			for(Zaposleni z: korisnik.getZaposleni()) {
				this.zaposleniDTO.add(new ZaposleniDTO(z));
			}
		}
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AdresaDTO getAdresaDTO() {
		return adresaDTO;
	}

	public void setAdresaDTO(AdresaDTO adresaDTO) {
		this.adresaDTO = adresaDTO;
	}

	public Set<ZaposleniDTO> getZaposleniDTO() {
		return zaposleniDTO;
	}

	public void setZaposleniDTO(Set<ZaposleniDTO> zaposleniDTO) {
		this.zaposleniDTO = zaposleniDTO;
	}
}
