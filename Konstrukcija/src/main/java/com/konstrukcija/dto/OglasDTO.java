package com.konstrukcija.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.konstrukcija.model.Komentar;
import com.konstrukcija.model.Ocena;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.service.KomentarService;

public class OglasDTO {

	@Autowired
	KomentarService komentarService;
	
	private Long id;
	private String datum_objave;
	private String datum_azuriranja;
	private String datum_isteka;
	private NekretninaDTO nekretninaDTO;
	private ObjavioDTO objavioDTO;
	private Set<KomentarDTO> komentarDTO;
	private Set<OcenaDTO> ocenaDTO;
	private double prosek;
	
	public OglasDTO() {}
	
	public OglasDTO(Long id, String datum_objave, String datum_azuriranja,
			String datum_isteka, NekretninaDTO nekretninaDTO,
			ObjavioDTO objavaDTO, Set<KomentarDTO> komentarDTO, Set<OcenaDTO> ocenaDTO) {
		super();
		this.id = id;
		this.datum_objave = datum_objave;
		this.datum_azuriranja = datum_azuriranja;
		this.datum_isteka = datum_isteka;
		this.nekretninaDTO = nekretninaDTO;
		this.objavioDTO = objavaDTO;
		this.komentarDTO = komentarDTO;
		this.ocenaDTO = ocenaDTO;
	}
	
	public OglasDTO(Oglas oglas) {
		this.id = oglas.getId();
		this.datum_objave = oglas.getDatum_objave();
		this.datum_azuriranja = oglas.getDatum_azuriranja();
		this.datum_isteka = oglas.getDatum_isteka();
		this.nekretninaDTO = new NekretninaDTO(oglas.getNekretnina());
		this.objavioDTO = new ObjavioDTO(oglas.getObjavio());
		this.komentarDTO = new HashSet<KomentarDTO>();
		for(Komentar k: oglas.getKomentar()) {
			
			this.komentarDTO.add(new KomentarDTO(k));
		}
		this.ocenaDTO = new HashSet<OcenaDTO>();
		double prosek1 = 0;
		int i = 0;
		for(Ocena o: oglas.getOcena()) {
			int oce = o.getOcena();
			prosek1 += oce;
			i++;
		}
		double prosek2 = prosek1/i;
		this.prosek = prosek2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatum_objave() {
		return datum_objave;
	}

	public void setDatum_objave(String datum_objave) {
		this.datum_objave = datum_objave;
	}

	public String getDatum_azuriranja() {
		return datum_azuriranja;
	}

	public void setDatum_azuriranja(String datum_azuriranja) {
		this.datum_azuriranja = datum_azuriranja;
	}

	public String getDatum_isteka() {
		return datum_isteka;
	}

	public void setDatum_isteka(String datum_isteka) {
		this.datum_isteka = datum_isteka;
	}

	public NekretninaDTO getNekretninaDTO() {
		return nekretninaDTO;
	}

	public void setNekretninaDTO(NekretninaDTO nekretninaDTO) {
		this.nekretninaDTO = nekretninaDTO;
	}

	public ObjavioDTO getObjavioDTO() {
		return objavioDTO;
	}

	public void setObjavioDTO(ObjavioDTO objavioDTO) {
		this.objavioDTO = objavioDTO;
	}

	public Set<KomentarDTO> getKomentarDTO() {
		return komentarDTO;
	}

	public void setKomentarDTO(Set<KomentarDTO> komentarDTO) {
		this.komentarDTO = komentarDTO;
	}

	public Set<OcenaDTO> getOcenaDTO() {
		return ocenaDTO;
	}

	public void setOcenaDTO(Set<OcenaDTO> ocenaDTO) {
		this.ocenaDTO = ocenaDTO;
	}

	public double getProsek() {
		return prosek;
	}

	public void setProsek(double prosek) {
		this.prosek = prosek;
	}
}
