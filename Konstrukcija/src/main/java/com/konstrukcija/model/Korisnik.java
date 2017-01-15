package com.konstrukcija.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Korisnik {
	@Id
	@GeneratedValue
	private Long id;
	private String fname;
	private String lname;
	private String password;
	private boolean active;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String username;
	
	@Column(nullable = true)
	private Boolean verified;
	
	@Column(nullable = true)
	private String verifyCode;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Adresa adresa;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "korisnik", cascade = CascadeType.ALL)
	private UserAuthority userAuthorities;

	@OneToMany(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Zaposleni> zaposleni = new HashSet<Zaposleni>();
	
	@OneToMany(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Objavio> objavio = new HashSet<Objavio>();
	
	@OneToMany(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<PrijavaOglasa> prijavaOglasa = new HashSet<PrijavaOglasa>();
	
	@OneToMany(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Komentar> komentar = new HashSet<Komentar>();

	public Set<Objavio> getObjavio() {
		return objavio;
	}

	public void setObjavio(Set<Objavio> objavio) {
		this.objavio = objavio;
	}

	public Set<PrijavaOglasa> getPrijavaOglasa() {
		return prijavaOglasa;
	}

	public void setPrijavaOglasa(Set<PrijavaOglasa> prijavaOglasa) {
		this.prijavaOglasa = prijavaOglasa;
	}

	public Set<Komentar> getKomentar() {
		return komentar;
	}

	public void setKomentar(Set<Komentar> komentar) {
		this.komentar = komentar;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	
	public UserAuthority getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(UserAuthority userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	public Set<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Set<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
