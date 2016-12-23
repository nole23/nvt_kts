package com.konstrukcija.dto;

import com.konstrukcija.model.Adresa;
import com.konstrukcija.model.Kompanija;

public class KompanijaDTO {

	private Long id;
	private String nazivKompanije;
	private String link;
	private Adresa adresa;
	
	public KompanijaDTO() {}
	
	public KompanijaDTO(Long id, String nazivKompanija, String link, Adresa adresa) {
		super();
		this.id = id;
		this.nazivKompanije = nazivKompanija;
		this.link = link;
		this.adresa = adresa;
	}
	
	public KompanijaDTO(Kompanija komanija) {
		this.id = komanija.getId();
		this.nazivKompanije = komanija.getNazivKomapnije();
		this.link = komanija.getLink();
		this.adresa = komanija.getAdresa();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivKompanije() {
		return nazivKompanije;
	}

	public void setNazivKompanije(String nazivKompanije) {
		this.nazivKompanije = nazivKompanije;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
}
