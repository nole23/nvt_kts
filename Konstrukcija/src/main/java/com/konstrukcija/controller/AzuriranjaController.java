package com.konstrukcija.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.konstrukcija.dto.ImageDTO;
import com.konstrukcija.dto.LokacijaDTO;
import com.konstrukcija.dto.KorisnikDTO;
import com.konstrukcija.dto.MessageDTO;
import com.konstrukcija.dto.TehnickaOpremljenostDTO;
import com.konstrukcija.model.Image;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Lokacija;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.TehnickaOpremljenost;
import com.konstrukcija.repository.ImageRepository;
import com.konstrukcija.service.KorisnikService;
import com.konstrukcija.service.LokacijaService;
import com.konstrukcija.service.NekretnineService;
import com.konstrukcija.service.TehnickaOpremljenostService;

@RestController
@RequestMapping(value = "api/lokacija")
public class AzuriranjaController {

	@Autowired
	private LokacijaService lokacijaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private TehnickaOpremljenostService tehnickaOpremljenostService;
	
	@Autowired
	private NekretnineService nekretninaSerive;
	
	@Autowired
	private ImageRepository imageRepository;
	
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
	@RequestMapping(value = "/gps", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> updatGPSLokacije(@RequestBody LokacijaDTO lokacijaDTO) {
		
		Lokacija lokacija = lokacijaService.findOne(lokacijaDTO.getId());
		
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
	@RequestMapping(value = "/tehnicka/{id}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getTehnickaNekretnine(@RequestBody TehnickaOpremljenostDTO tehnickaOpremljenostDTO, @PathVariable Long id) {
		
		TehnickaOpremljenost tehnickaOpremljenost = tehnickaOpremljenostService.findOne(id);
		
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
	
	@RequestMapping(value = "/update/users", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageDTO> updateKorisnik(Principal principal, @RequestBody KorisnikDTO korisnikDTO) {

		MessageDTO mesageDTO = new MessageDTO();
		
		Korisnik korisnik = korisnikService.findByUsername(principal.getName());

		korisnik.setFname(korisnikDTO.getFname());
		korisnik.setLname(korisnikDTO.getLname());
		korisnik.setUsername(korisnikDTO.getUsername());

		korisnik = korisnikService.save(korisnik);
		
		mesageDTO.setSuccess("azuriraneInformacije");
		return new ResponseEntity<MessageDTO>(mesageDTO, HttpStatus.OK);
	}
	
	
	/*
	@RequestMapping(value = "add/image/{idNekretnine}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageDTO> uploadPictureImage(Principal principal, @RequestBody MultipartFile file, @PathVariable Long idNekretnine) {

		MessageDTO mesageDTO = new MessageDTO();
		
		Korisnik korisnik = korisnikService.findByUsername(principal.getName());
		if(korisnik == null) {
			mesageDTO.setError("nisteUlogovani");
			return new ResponseEntity<MessageDTO>(mesageDTO, HttpStatus.OK);
		}
		Nekretnina nekretnina = nekretninaSerive.findOne(idNekretnine);
		Image image = new Image();
		image.setKorisnik(korisnik);
		image.setNekretnina(nekretnina);
		image.setPicture(imageDTO.getPicture());
		
		 if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream = 
	                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
	                stream.write(bytes);
	                stream.close();
	               
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name + " because the file was empty.";
	        }
		
		imageRepository.save(image);
		
		mesageDTO.setSuccess("slikaDodat");
		return new ResponseEntity<MessageDTO>(mesageDTO, HttpStatus.OK);
	}*/
}
