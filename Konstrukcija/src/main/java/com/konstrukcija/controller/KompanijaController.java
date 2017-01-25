package com.konstrukcija.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.AdresaDTO;
import com.konstrukcija.dto.KompanijaDTO;
import com.konstrukcija.dto.MessageDTO;
import com.konstrukcija.dto.SviZaposleniDTO;
import com.konstrukcija.model.Adresa;
import com.konstrukcija.model.Kompanija;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Zaposleni;
import com.konstrukcija.repository.AdresaRepository;
import com.konstrukcija.repository.KompanijaRepository;
import com.konstrukcija.repository.ZaposleniRepository;
import com.konstrukcija.service.KorisnikService;
import com.konstrukcija.service.ZaposleniService;

@RestController
@RequestMapping(value = "api/kompanija")
public class KompanijaController {

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private ZaposleniRepository zaposleniRepositoy;

	@Autowired
	private ZaposleniService zaposleniService;

	@Autowired
	private AdresaRepository adresaService;

	@Autowired
	private KompanijaRepository kompanijaRepository;

	/**
	 * Dodati korisnika u u firmu kao zaposleni
	 * 
	 * @param idKorisnik
	 * @param idKompanija
	 * @return
	 */
	@RequestMapping(value = "/kompanija/{idKorisnik}", method = RequestMethod.GET)
	public ResponseEntity<MessageDTO> addKorisnikKompanija(
			@PathVariable Long idKorisnik, Principal principal) {

		MessageDTO messageDTO = new MessageDTO();
		
		Korisnik admin = korisnikService.findByUsername(principal.getName());
		Zaposleni zaposleni = zaposleniRepositoy.findByKorisnik(admin);

		Kompanija kompanija = kompanijaRepository.findByZaposleni(zaposleni);

		Korisnik korisnik = korisnikService.findOne(idKorisnik);
		
		Zaposleni zap = zaposleniRepositoy.findByKorisnik(korisnik);
		if(zap != null) {
			messageDTO.setError("neuspenos");
			return new ResponseEntity<>(messageDTO,HttpStatus.OK);
		}

		Zaposleni zaposleni1 = new Zaposleni();

		zaposleni1.setKompanija(kompanija);
		zaposleni1.setKorisnik(korisnik);
		zaposleni1.setUloga("");

		zaposleniRepositoy.save(zaposleni1);
		messageDTO.setSuccess("uspesno");
		return new ResponseEntity<>(messageDTO,HttpStatus.OK);
	}

	/**
	 * Kreiranje nove kompanije i automatski postajete njen admin
	 * 
	 * @param idKorisnik
	 * @param kompanijaDTO
	 * @return
	 */
	@RequestMapping(value = "/add/kompanija", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageDTO> addKompanija(Principal principal,
			@RequestBody KompanijaDTO kompanijaDTO) {
		MessageDTO messageDTO = new MessageDTO();

		Korisnik korisnik = korisnikService.findByUsername(principal.getName());
		List<Zaposleni> zaposleni1 = zaposleniRepositoy.findAll();
		for (Zaposleni z : zaposleni1) {
			if (z.getKorisnik() == korisnik) {
				if (z.getUloga().equals("gazda"))
					messageDTO.setError("nisteDodali");
				return new ResponseEntity<MessageDTO>(messageDTO,
						HttpStatus.BAD_REQUEST);

			}
		}

		Kompanija kompanija = new Kompanija();
		Zaposleni zaposleni = new Zaposleni();

		kompanija.setNazivKomapnije(kompanijaDTO.getNazivKomapnije());
		kompanija.setLink(kompanijaDTO.getLink());

		zaposleni.setKompanija(kompanija);
		zaposleni.setKorisnik(korisnik);
		zaposleni.setUloga("gazda");

		kompanijaRepository.save(kompanija);
		zaposleniRepositoy.save(zaposleni);

		messageDTO.setSuccess("dodato");
		return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/kompanija/{idKompanija}/adresa", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getKompanijaAdresa(
			@PathVariable Long idKompanija, @RequestBody AdresaDTO adresaDTO) {

		Kompanija kompanija = kompanijaRepository.findOne(idKompanija);
		Adresa adresa = new Adresa();

		adresa.setDrzava(adresaDTO.getDrzava());
		adresa.setGrad(adresaDTO.getGrad());
		adresa.setUlica(adresaDTO.getUlica());
		adresa.setBroj_ulice(adresaDTO.getBroj_ulice());
		adresa.setBroj_zgrade(adresaDTO.getBroj_zgrade());
		adresa.setBroj_stama(adresaDTO.getBroj_stama());

		kompanija.setAdresa(adresa);

		adresaService.save(adresa);
		kompanijaRepository.save(kompanija);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Metoda koja vraca kompaniju pomocu korisnika koji je ulogovan i radi u
	 * njoj ukoliko ne radi ni u jednoj kompaniji nece moci da pronadje trazenu
	 * kompaniju.
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/employed/company", method = RequestMethod.GET)
	public ResponseEntity<SviZaposleniDTO> getMyCompani(Principal principal) {

		Korisnik korisnik = korisnikService.findByUsername(principal.getName());
		if (korisnik == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Zaposleni zaposleni = zaposleniRepositoy.findByKorisnik(korisnik);
		if (zaposleni == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(new SviZaposleniDTO(zaposleni),
				HttpStatus.OK);
	}

	/**
	 * Ispis svih zaposlenih jedne kompanije
	 * 
	 * @param principal
	 * @param idCompany
	 *            , id kompanije
	 * @return
	 */
	@RequestMapping(value = "/company/users/{idCompany}", method = RequestMethod.GET)
	public ResponseEntity<List<SviZaposleniDTO>> getUserCompany(
			Principal principal, @PathVariable Long idCompany) {

		Korisnik korisnik = korisnikService.findByUsername(principal.getName());
		if (korisnik == null) {
			return new ResponseEntity<>(
					HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}

		Long id = korisnik.getId();
		Zaposleni za = zaposleniRepositoy.findOne(id);
		Long idKompanija = za.getId();
		Kompanija kompanija = kompanijaRepository.findOne(idKompanija);

		List<Zaposleni> zaposleni = zaposleniRepositoy
				.findAllByKompanija(kompanija);

		List<SviZaposleniDTO> zaposleniDTO = new ArrayList<>();
		for (Zaposleni z : zaposleni) {
			zaposleniDTO.add(new SviZaposleniDTO(z));
		}

		return new ResponseEntity<>(zaposleniDTO, HttpStatus.OK);
	}

	/**
	 * Na osnovu id zaposlenog brisemo korisnika iz liste zaposlenih
	 * 
	 * @param principal
	 * @param idKorisnik
	 * @return
	 */
	@RequestMapping(value = "/delete/users/{idKorisnik}", method = RequestMethod.DELETE)
	public ResponseEntity<String> brisanjeZaposlenog(Principal principal,
			@PathVariable Long idKorisnik) {

		Korisnik korisnik = korisnikService.findByUsername(principal.getName());
		Zaposleni zaposleni = zaposleniRepositoy.findByKorisnik(korisnik);
		if (!zaposleni.getUloga().equals("gazda"))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		zaposleniService.remove(idKorisnik);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
