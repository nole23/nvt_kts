package com.konstrukcija.dto;

import com.konstrukcija.model.Kompanija;
import com.konstrukcija.model.Objavio;

public class ObjavioDTO {

	private Long id;
	private NekretninaDTO nekretninaDTO;
	private Kompanija kompanija;
	
	public ObjavioDTO() {}
	
	public ObjavioDTO(Long id, NekretninaDTO nekretninaDTO, Kompanija kompanija) {
		super();
		this.id = id;
		this.nekretninaDTO = nekretninaDTO;
		this.kompanija = kompanija;
	}
	
	public ObjavioDTO(Objavio objavio) {
		this.id = objavio.getId();
		this.nekretninaDTO = new NekretninaDTO(objavio.getNekretnina());
		this.kompanija = objavio.getKompanija();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NekretninaDTO getNekretninaDTO() {
		return nekretninaDTO;
	}

	public void setNekretninaDTO(NekretninaDTO nekretninaDTO) {
		this.nekretninaDTO = nekretninaDTO;
	}

	public Kompanija getKompanija() {
		return kompanija;
	}

	public void setKompanija(Kompanija kompanija) {
		this.kompanija = kompanija;
	}

	@Override
	public String toString() {
		return "ObjavioDTO [id=" + id + ", nekretninaDTO=" + nekretninaDTO + ", kompanija="
				+ kompanija + "]";
	}
}
