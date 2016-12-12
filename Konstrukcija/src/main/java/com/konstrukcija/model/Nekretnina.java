package com.konstrukcija.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Nekretnina {

	@Id
	@GeneratedValue
	private Integer id;
	private String naziv_nekretnine;
	private Double cena;
	private Double povrsina;
	private String sobnost;
	private String stanje_objekta;
	private String grejanje;
	private String spratova;
	private String stanje;
	private String sprat;
	private String opis;
	
	@OneToMany(mappedBy = "nekretnina", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Objava> objava = new HashSet<Objava>();
	
	@OneToMany(mappedBy = "nekretnina", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<KategorijaNekretnine> kategorijaNekretnine = new HashSet<KategorijaNekretnine>();
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Lokacija lokacija;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private TehnickaOpremljenost tehnickaOpremljenost;
	
	@OneToMany(mappedBy = "nekretnina", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Oglasi> oglasi = new HashSet<Oglasi>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv_nekretnine() {
		return naziv_nekretnine;
	}

	public void setNaziv_nekretnine(String naziv_nekretnine) {
		this.naziv_nekretnine = naziv_nekretnine;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Double getPovrsina() {
		return povrsina;
	}

	public void setPovrsina(Double povrsina) {
		this.povrsina = povrsina;
	}

	public String getSobnost() {
		return sobnost;
	}

	public void setSobnost(String sobnost) {
		this.sobnost = sobnost;
	}

	public String getStanje_objekta() {
		return stanje_objekta;
	}

	public void setStanje_objekta(String stanje_objekta) {
		this.stanje_objekta = stanje_objekta;
	}

	public String getGrejanje() {
		return grejanje;
	}

	public void setGrejanje(String grejanje) {
		this.grejanje = grejanje;
	}

	public String getSpratova() {
		return spratova;
	}

	public void setSpratova(String spratova) {
		this.spratova = spratova;
	}

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	public String getSprat() {
		return sprat;
	}

	public void setSprat(String sprat) {
		this.sprat = sprat;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<Objava> getObjava() {
		return objava;
	}

	public void setObjava(Set<Objava> objava) {
		this.objava = objava;
	}

	public Set<KategorijaNekretnine> getKategorijaNekretnine() {
		return kategorijaNekretnine;
	}

	public void setKategorijaNekretnine(
			Set<KategorijaNekretnine> kategorijaNekretnine) {
		this.kategorijaNekretnine = kategorijaNekretnine;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public TehnickaOpremljenost getTehnickaOpremljenost() {
		return tehnickaOpremljenost;
	}

	public void setTehnickaOpremljenost(TehnickaOpremljenost tehnickaOpremljenost) {
		this.tehnickaOpremljenost = tehnickaOpremljenost;
	}

	public Set<Oglasi> getOglasi() {
		return oglasi;
	}

	public void setOglasi(Set<Oglasi> oglasi) {
		this.oglasi = oglasi;
	}
}
