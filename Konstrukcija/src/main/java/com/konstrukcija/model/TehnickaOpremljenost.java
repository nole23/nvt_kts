package com.konstrukcija.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TehnickaOpremljenost {

	@Id
	@GeneratedValue
	private Long id;
	private Boolean tarasa;
	private Boolean telefon;
	private Boolean garaua;
	private Boolean kablovska;
	private Boolean pogled_na_grad;
	private Boolean podrum;
	private Boolean kamin;
	private Boolean bazen;
	private Boolean internet;
	private Boolean tavan;
	private Boolean klima;
	private Boolean pogled_na_more;
	private Boolean lift;
	private Boolean strija;
	private Boolean voda;
	private Boolean kanalizacija;
	private Boolean gas;
	
	@OneToMany(mappedBy = "tehnickaOpremljenost", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Nekretnina> nekretnina = new HashSet<Nekretnina>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getTarasa() {
		return tarasa;
	}

	public void setTarasa(Boolean tarasa) {
		this.tarasa = tarasa;
	}

	public Boolean getTelefon() {
		return telefon;
	}

	public void setTelefon(Boolean telefon) {
		this.telefon = telefon;
	}

	public Boolean getGaraua() {
		return garaua;
	}

	public void setGaraua(Boolean garaua) {
		this.garaua = garaua;
	}

	public Boolean getKablovska() {
		return kablovska;
	}

	public void setKablovska(Boolean kablovska) {
		this.kablovska = kablovska;
	}

	public Boolean getPogled_na_grad() {
		return pogled_na_grad;
	}

	public void setPogled_na_grad(Boolean pogled_na_grad) {
		this.pogled_na_grad = pogled_na_grad;
	}

	public Boolean getPodrum() {
		return podrum;
	}

	public void setPodrum(Boolean podrum) {
		this.podrum = podrum;
	}

	public Boolean getKamin() {
		return kamin;
	}

	public void setKamin(Boolean kamin) {
		this.kamin = kamin;
	}

	public Boolean getBazen() {
		return bazen;
	}

	public void setBazen(Boolean bazen) {
		this.bazen = bazen;
	}

	public Boolean getInternet() {
		return internet;
	}

	public void setInternet(Boolean internet) {
		this.internet = internet;
	}

	public Boolean getTavan() {
		return tavan;
	}

	public void setTavan(Boolean tavan) {
		this.tavan = tavan;
	}

	public Boolean getKlima() {
		return klima;
	}

	public void setKlima(Boolean klima) {
		this.klima = klima;
	}

	public Boolean getPogled_na_more() {
		return pogled_na_more;
	}

	public void setPogled_na_more(Boolean pogled_na_more) {
		this.pogled_na_more = pogled_na_more;
	}

	public Boolean getLift() {
		return lift;
	}

	public void setLift(Boolean lift) {
		this.lift = lift;
	}

	public Boolean getStrija() {
		return strija;
	}

	public void setStrija(Boolean strija) {
		this.strija = strija;
	}

	public Boolean getVoda() {
		return voda;
	}

	public void setVoda(Boolean voda) {
		this.voda = voda;
	}

	public Boolean getKanalizacija() {
		return kanalizacija;
	}

	public void setKanalizacija(Boolean kanalizacija) {
		this.kanalizacija = kanalizacija;
	}

	public Boolean getGas() {
		return gas;
	}

	public void setGas(Boolean gas) {
		this.gas = gas;
	}

	public Set<Nekretnina> getNekretnina() {
		return nekretnina;
	}

	public void setNekretnina(Set<Nekretnina> nekretnina) {
		this.nekretnina = nekretnina;
	}

	@Override
	public String toString() {
		return "TehnickaOpremljenost [id=" + id + ", tarasa=" + tarasa
				+ ", telefon=" + telefon + ", garaua=" + garaua
				+ ", kablovska=" + kablovska + ", pogled_na_grad="
				+ pogled_na_grad + ", podrum=" + podrum + ", kamin=" + kamin
				+ ", bazen=" + bazen + ", internet=" + internet + ", tavan="
				+ tavan + ", klima=" + klima + ", pogled_na_more="
				+ pogled_na_more + ", lift=" + lift + ", strija=" + strija
				+ ", voda=" + voda + ", kanalizacija=" + kanalizacija
				+ ", gas=" + gas + ", nekretnina=" + nekretnina + "]";
	}
}
