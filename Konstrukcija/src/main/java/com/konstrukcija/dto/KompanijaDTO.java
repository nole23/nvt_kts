package com.konstrukcija.dto;

import java.util.HashSet;
import java.util.Set;

import com.konstrukcija.model.Kompanija;
import com.konstrukcija.model.Objavio;

public class KompanijaDTO {

	private Long id;
	private String nazivKomapnije;
	private String link;
	private AdresaDTO adresaDTO;
	private Set<ObjavioDTO> objavioDTO;
	
public KompanijaDTO() {}
	
	public KompanijaDTO(Long id, String nazivKomapnije, String link, AdresaDTO adresaDTO, Set<ObjavioDTO> objavioDTO) {
		super();
		this.id = id;
		this.nazivKomapnije = nazivKomapnije;
		this.link = link;
		this.adresaDTO = adresaDTO;
		this.objavioDTO = objavioDTO;
	}
	
	public KompanijaDTO(Kompanija kompanija) {
		this.id = kompanija.getId();
		this.nazivKomapnije = kompanija.getNazivKomapnije();
		this.link = kompanija.getLink();
		if(adresaDTO != null)
			this.adresaDTO = new AdresaDTO(kompanija.getAdresa());
		if(objavioDTO != null){
			this.objavioDTO = new HashSet<ObjavioDTO>();
			for(Objavio o: kompanija.getObjavio()) {
				this.objavioDTO.add(new ObjavioDTO(o));
			}
		}
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

	public Set<ObjavioDTO> getObjavioDTO() {
		return objavioDTO;
	}

	public void setObjavioDTO(Set<ObjavioDTO> objavioDTO) {
		this.objavioDTO = objavioDTO;
	}

	
}
