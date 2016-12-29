package com.konstrukcija.dto;

import com.konstrukcija.model.Kompanija;

public class KompanijaDTO {

	private Long id;
	private String nazivKomapnije;
	private String link;
	private AdresaDTO adresaDTO;
	
public KompanijaDTO() {}
	
	public KompanijaDTO(Long id, String nazivKomapnije, String link, AdresaDTO adresaDTO) {
		super();
		this.id = id;
		this.nazivKomapnije = nazivKomapnije;
		this.link = link;
		this.adresaDTO = adresaDTO;
	}
	
	public KompanijaDTO(Kompanija kompanija) {
		this.id = kompanija.getId();
		this.nazivKomapnije = kompanija.getNazivKomapnije();
		this.link = kompanija.getLink();
		this.adresaDTO = new AdresaDTO(kompanija.getAdresa());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivKomapnije() {
		return nazivKomapnije;
	}

	public void setNazivKomapnije(String nazivKomapnije) {
		this.nazivKomapnije = nazivKomapnije;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public AdresaDTO getAdresaDTO() {
		return adresaDTO;
	}

	public void setAdresaDTO(AdresaDTO adresaDTO) {
		this.adresaDTO = adresaDTO;
	}

	@Override
	public String toString() {
		return "KompanijaDTO [id=" + id + ", nazivKomapnije=" + nazivKomapnije
				+ ", link=" + link + ", adresaDTO=" + adresaDTO + "]";
	}
}
