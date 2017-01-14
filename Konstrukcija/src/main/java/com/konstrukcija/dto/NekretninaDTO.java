package com.konstrukcija.dto;

import java.util.HashSet;
import java.util.Set;

import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Objavio;

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
	private KategorijaDTO kategorijaDTO;
	private LokacijaDTO lokacijaDTO;
	private TehnickaOpremljenostDTO tehnickaOpremljenostDTO;
	private Set<ObjavioDTO> objavioDTO;
	
public NekretninaDTO() {}
	
	public NekretninaDTO(Long id, String naziv_nekretnine, Double cena, Double povrsina, String sobnost, String stanje_objekta, 
			String grejanje, String spratova, String stanje, String sprat, String opis, LokacijaDTO lokacijaDTO, TehnickaOpremljenostDTO tehnickaOpremljenostDTO,
			KategorijaDTO kategorijaDTO, Set<ObjavioDTO> objavioDTO) {
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
		this.lokacijaDTO = lokacijaDTO;
		this.tehnickaOpremljenostDTO = tehnickaOpremljenostDTO;
		this.kategorijaDTO = kategorijaDTO;
		this.objavioDTO = objavioDTO;
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
		this.lokacijaDTO = new LokacijaDTO(nekretnina.getLokacija());
		this.tehnickaOpremljenostDTO = new TehnickaOpremljenostDTO(nekretnina.getTehnickaOpremljenost());
		this.kategorijaDTO = new KategorijaDTO(nekretnina.getKategorija());
		this.objavioDTO = new HashSet<ObjavioDTO>();
		for(Objavio o: nekretnina.getObjavio()) {
			
			this.objavioDTO.add(new ObjavioDTO(o));
		}
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

	public KategorijaDTO getKategorijaDTO() {
		return kategorijaDTO;
	}

	public void setKategorijaDTO(KategorijaDTO kategorijaDTO) {
		this.kategorijaDTO = kategorijaDTO;
	}

	public LokacijaDTO getLokacijaDTO() {
		return lokacijaDTO;
	}

	public void setLokacijaDTO(LokacijaDTO lokacijaDTO) {
		this.lokacijaDTO = lokacijaDTO;
	}

	public TehnickaOpremljenostDTO getTehnickaOpremljenostDTO() {
		return tehnickaOpremljenostDTO;
	}

	public void setTehnickaOpremljenostDTO(
			TehnickaOpremljenostDTO tehnickaOpremljenostDTO) {
		this.tehnickaOpremljenostDTO = tehnickaOpremljenostDTO;
	}

	public Set<ObjavioDTO> getObjavioDTO() {
		return objavioDTO;
	}

	public void setObjavioDTO(Set<ObjavioDTO> objavioDTO) {
		this.objavioDTO = objavioDTO;
	}
}
