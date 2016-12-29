package com.konstrukcija.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.LokacijaDTO;
import com.konstrukcija.dto.TehnickaOpremljenostDTO;
import com.konstrukcija.model.Lokacija;
import com.konstrukcija.model.TehnickaOpremljenost;
import com.konstrukcija.service.LokacijaService;
import com.konstrukcija.service.TehnickaOpremljenostService;

@RestController
@RequestMapping(value = "api/lokacija")
public class AzuriranjaController {

	@Autowired
	private LokacijaService lokacijaService;
	
	@Autowired
	private TehnickaOpremljenostService tehnickaOpremljenostService;
	
	/**
	 * Azuriranje podataka lokacije u slucaju da se promeni ulica il broj zgrade
	 * @param idLokacija, id lokacije koju zelimo da azuriramo
	 * @param lokacijaDTO, podaci lokacije
	 * @return na osnovu idLokacija mi azuriramo podatke lokacije
	 */
	@RequestMapping(value = "/update/{idLokacija}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> updateLokacija(@PathVariable Long idLokacija, @RequestBody LokacijaDTO lokacijaDTO) {
		
		Lokacija lokacija = lokacijaService.findOne(idLokacija);
		
		lokacija.setOblas(lokacijaDTO.getOblas());
		lokacija.setUlica(lokacijaDTO.getUlica());
		lokacija.setBroj_ulice(lokacijaDTO.getBroj_ulice());
		lokacija.setBroj_zgrade(lokacijaDTO.getBroj_zgrade());
		lokacija.setBroj_stana(lokacijaDTO.getBroj_stana());
		
		lokacija = lokacijaService.save(lokacija);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * dodati GPS poziciju za odredjenu lokaciju
	 * @param idLokacija
	 * @param lokacijaDTO
	 * @return
	 */
	@RequestMapping(value = "/gps/{idLokacija}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> updatGPSLokacije(@PathVariable Long idLokacija, @RequestBody LokacijaDTO lokacijaDTO) {
		
		Lokacija lokacija = lokacijaService.findOne(idLokacija);
		
		lokacija.setGeo_duzina(lokacijaDTO.getGeo_duzina());
		lokacija.setGeo_sirina(lokacijaDTO.getGeo_sirina());
		
		lokacija = lokacijaService.save(lokacija);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Azuriranje tehnicke opremljenosti za datu nekretninu
	 * @param idTehnica
	 * @param tehnickaOpremljenostDTO
	 * @return
	 */
	@RequestMapping(value = "/tehnicka/{idTehnica}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getTehnickaNekretnine(@PathVariable Long idTehnica, @RequestBody TehnickaOpremljenostDTO tehnickaOpremljenostDTO) {
		
		TehnickaOpremljenost tehnickaOpremljenost = tehnickaOpremljenostService.findOne(idTehnica);
		
		tehnickaOpremljenost.setTarasa(tehnickaOpremljenostDTO.getTarasa());
		tehnickaOpremljenost.setTelefon(tehnickaOpremljenostDTO.getTelefon());
		tehnickaOpremljenost.setGaraua(tehnickaOpremljenostDTO.getGaraua());
		tehnickaOpremljenost.setKablovska(tehnickaOpremljenostDTO.getKablovska());
		tehnickaOpremljenost.setPogled_na_grad(tehnickaOpremljenostDTO.getPogled_na_grad());
		tehnickaOpremljenost.setPodrum(tehnickaOpremljenostDTO.getPodrum());
		tehnickaOpremljenost.setKamin(tehnickaOpremljenostDTO.getKamin());
		tehnickaOpremljenost.setBazen(tehnickaOpremljenostDTO.getBazen());
		tehnickaOpremljenost.setInternet(tehnickaOpremljenostDTO.getInternet());
		tehnickaOpremljenost.setTavan(tehnickaOpremljenostDTO.getTavan());
		tehnickaOpremljenost.setKlima(tehnickaOpremljenostDTO.getKlima());
		tehnickaOpremljenost.setPogled_na_more(tehnickaOpremljenostDTO.getPogled_na_more());
		tehnickaOpremljenost.setLift(tehnickaOpremljenostDTO.getLift());
		tehnickaOpremljenost.setStrija(tehnickaOpremljenostDTO.getStrija());
		tehnickaOpremljenost.setVoda(tehnickaOpremljenostDTO.getVoda());
		tehnickaOpremljenost.setKanalizacija(tehnickaOpremljenostDTO.getKanalizacija());
		tehnickaOpremljenost.setGas(tehnickaOpremljenostDTO.getGas());
		
		tehnickaOpremljenost = tehnickaOpremljenostService.save(tehnickaOpremljenost);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
