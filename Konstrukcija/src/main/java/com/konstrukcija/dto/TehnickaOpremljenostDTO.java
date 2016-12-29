package com.konstrukcija.dto;

import com.konstrukcija.model.TehnickaOpremljenost;

public class TehnickaOpremljenostDTO {

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
	
	public TehnickaOpremljenostDTO() {}
	
	public TehnickaOpremljenostDTO(Long id, Boolean tarasa, Boolean telefon, Boolean garaua, Boolean kablovska, Boolean pogled_na_grad, Boolean podrum, 
			Boolean kamin, Boolean bazen, Boolean internet, Boolean tavan, Boolean klima, Boolean pogled_na_more, Boolean lift, Boolean strija, Boolean voda,
			Boolean kanalizacija, Boolean gas){
		super();
		this.id = id;
		this.tarasa = tarasa;
		this.telefon = telefon;
		this.garaua = garaua;
		this.kablovska = kablovska;
		this.pogled_na_grad = pogled_na_grad;
		this.podrum = podrum;
		this.kamin = kamin;
		this.bazen = bazen;
		this.internet = internet;
		this.tavan = tavan;
		this.klima = klima;
		this.pogled_na_more = pogled_na_more;
		this.lift = lift;
		this.strija = strija;
		this.voda = voda;
		this.kanalizacija = kanalizacija;
		this.gas = gas;
	}
	
	public TehnickaOpremljenostDTO(TehnickaOpremljenost tehnickaOpremljenost) {
		this.id = tehnickaOpremljenost.getId();
		this.tarasa = tehnickaOpremljenost.getTarasa();
		this.telefon = tehnickaOpremljenost.getTelefon();
		this.garaua = tehnickaOpremljenost.getGaraua();
		this.kablovska = tehnickaOpremljenost.getKablovska();
		this.pogled_na_grad = tehnickaOpremljenost.getPogled_na_grad();
		this.podrum = tehnickaOpremljenost.getPodrum();
		this.kamin = tehnickaOpremljenost.getKamin();
		this.bazen = tehnickaOpremljenost.getBazen();
		this.internet = tehnickaOpremljenost.getInternet();
		this.tavan = tehnickaOpremljenost.getTavan();
		this.klima = tehnickaOpremljenost.getKlima();
		this.pogled_na_more = tehnickaOpremljenost.getPogled_na_more();
		this.lift = tehnickaOpremljenost.getLift();
		this.strija = tehnickaOpremljenost.getStrija();
		this.voda = tehnickaOpremljenost.getVoda();
		this.kanalizacija = tehnickaOpremljenost.getKanalizacija();
		this.gas = tehnickaOpremljenost.getGas();
	}

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
}
