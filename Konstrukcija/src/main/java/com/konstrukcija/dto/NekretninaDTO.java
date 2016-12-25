package com.konstrukcija.dto;

import com.konstrukcija.model.Lokacija;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.TehnickaOpremljenost;

public class NekretninaDTO {

	private Long id;
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
	private Lokacija lokacija;
	private TehnickaOpremljenost tehnickaOpremljenost;
	
	public NekretninaDTO() {}
	
	public NekretninaDTO(Long id, String naziv_nekretnine, Double cena, Double povrsina, String sobnost, String stanje_objekta, 
			String grejanje, String spratova, String stanje, String sprat, String opis, Lokacija lokacija, TehnickaOpremljenost tehnickaOpremljenost) {
		super();
		this.id = id;
		this.naziv_nekretnine = naziv_nekretnine;
		this.cena = cena;
		this.povrsina = povrsina;
		this.sobnost = sobnost;
		this.stanje_objekta = stanje_objekta;
		this.grejanje = grejanje;
		this.spratova = spratova;
		this.stanje = stanje;
		this.sprat = sprat;
		this.opis = opis;
		this.lokacija = lokacija;
		this.tehnickaOpremljenost = tehnickaOpremljenost;
	}
	
	public NekretninaDTO(Nekretnina nekretnina) {
		super();
		this.id = nekretnina.getId();
		this.naziv_nekretnine = nekretnina.getNaziv_nekretnine();
		this.cena = nekretnina.getCena();
		this.povrsina = nekretnina.getPovrsina();
		this.sobnost = nekretnina.getSobnost();
		this.stanje_objekta = nekretnina.getStanje_objekta();
		this.grejanje = nekretnina.getGrejanje();
		this.spratova = nekretnina.getSpratova();
		this.stanje = nekretnina.getStanje();
		this.sprat = nekretnina.getSprat();
		this.opis = nekretnina.getOpis();
		this.lokacija = nekretnina.getLokacija();
		this.tehnickaOpremljenost = nekretnina.getTehnickaOpremljenost();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "NekretninaDTO [id=" + id + ", naziv_nekretnine="
				+ naziv_nekretnine + ", cena=" + cena + ", povrsina="
				+ povrsina + ", sobnost=" + sobnost + ", stanje_objekta="
				+ stanje_objekta + ", grejanje=" + grejanje + ", spratova="
				+ spratova + ", stanje=" + stanje + ", sprat=" + sprat
				+ ", opis=" + opis + ", lokacija=" + lokacija
				+ ", tehnickaOpremljenost=" + tehnickaOpremljenost + "]";
	}

	
	
	
}
