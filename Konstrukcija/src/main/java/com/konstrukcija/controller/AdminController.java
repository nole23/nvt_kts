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

import com.konstrukcija.dto.KategorijaDTO;
import com.konstrukcija.dto.KorisnikDTO;
import com.konstrukcija.dto.MessageDTO;
import com.konstrukcija.dto.PrijavaOglasaDTO;
import com.konstrukcija.model.Admin;
import com.konstrukcija.model.Kategorija;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Lokacija;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Objavio;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.model.PrijavaOglasa;
import com.konstrukcija.model.TehnickaOpremljenost;
import com.konstrukcija.model.UserAuthority;
import com.konstrukcija.repository.AdminRepository;
import com.konstrukcija.repository.KategorijaRepository;
import com.konstrukcija.repository.ObjavioRepository;
import com.konstrukcija.repository.OglasRepository;
import com.konstrukcija.repository.PrijavaOglasaRepository;
import com.konstrukcija.repository.UserAuthorityRepository;
import com.konstrukcija.service.KorisnikService;
import com.konstrukcija.service.LokacijaService;
import com.konstrukcija.service.NekretnineService;
import com.konstrukcija.service.ObjavioService;
import com.konstrukcija.service.TehnickaOpremljenostService;

@RestController
@RequestMapping(value = "api/admin")
public class AdminController {
	
	@Autowired
	private KategorijaRepository kategorijaService;
	
	
	@Autowired
	private NekretnineService nekretninaService;
	
	@Autowired
	private TehnickaOpremljenostService tehnicakOpremljenostService;
	
	@Autowired
	private LokacijaService lokacijaService;
	
	@Autowired ObjavioRepository objavioRepository;
	
	@Autowired
	private ObjavioService objavioService;
	
	@Autowired
	private OglasRepository oglasService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private UserAuthorityRepository userAuthorityRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PrijavaOglasaRepository prijavljenOglasRepository;
	
	@RequestMapping(value = "/add/admin/{idKorisnik}}", method = RequestMethod.GET)
	public ResponseEntity<MessageDTO> addAdmin(Principal principal, @PathVariable Long idKorisnik) {

		MessageDTO mesageDTO = new MessageDTO();
		
		Korisnik admin = korisnikService.findByUsername(principal.getName());
		if(admin.getUserAuthorities().getAdmin().getName().equals("admin")){
			mesageDTO.setError("niste admin");
			return new ResponseEntity<MessageDTO>(mesageDTO, HttpStatus.BAD_REQUEST);
		}
		
		Korisnik korisnik = korisnikService.findOne(idKorisnik);
		UserAuthority userAuthority = userAuthorityRepository.findByKorisnik(korisnik);
		String id = "1";
		Admin admin1 = adminRepository.findOne(Long.parseLong(id));
		userAuthority.setAdmin(admin1);
		
		userAuthorityRepository.save(userAuthority);
		
		mesageDTO.setSuccess("dodali admina");
		return new ResponseEntity<MessageDTO>(mesageDTO, HttpStatus.OK);
	}
	
	/**
	 * Cuvanje nove kategorije, moze samo menadzer sistema da uradi
	 * @param principal
	 * @param kategorijaDTO, parametri kategorije
	 * @return dodata nova kategorija
	 */
	@RequestMapping(value = "/add/kategorija", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<KategorijaDTO> saveKategorija(Principal principal, @RequestBody KategorijaDTO kategorijaDTO) {
		
		Kategorija kategorija = new Kategorija();
		
		kategorija.setName(kategorijaDTO.getName());
		kategorija.setTip(kategorijaDTO.getTip());
		
		kategorijaService.save(kategorija);
		
		return new ResponseEntity<>(new KategorijaDTO(kategorija),HttpStatus.OK);
	}
	
	/**
	 * Pregled svih prijavljenih oglasa
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/prijavljena/nekretnina", method = RequestMethod.GET)
	public ResponseEntity<List<PrijavaOglasaDTO>> getPrijavljenaNekretnina(Principal principal) {
		
		List<PrijavaOglasa> prijavljenOglas = prijavljenOglasRepository.findAll();
		
		List<PrijavaOglasaDTO> prijavaOglasaDTO = new ArrayList<>();
		for(PrijavaOglasa o: prijavljenOglas) {
			prijavaOglasaDTO.add(new PrijavaOglasaDTO(o));
		}
		
		return new ResponseEntity<>(prijavaOglasaDTO,HttpStatus.OK);
	}
	
	/**
	 * Metoda koja brise datu nekretninu
	 * @param principal
	 * @param idNekretnina nekretnina koju trebamo da obrisemo
	 * @return pre nego se izbirse nekretnina treba izbrisati podatke iz zavisnih tabela kao sto su
	 * lokacija, tehnickaOpremljenost, objavio i oglas
	 */
	@RequestMapping(value = "/delete/nekretnina/{idNekretnina}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteNekretnina(Principal principal, @PathVariable Long idNekretnina) {
		
		Nekretnina nekretnina = nekretninaService.findOne(idNekretnina);
		TehnickaOpremljenost tehnicka = nekretnina.getTehnickaOpremljenost();
		Lokacija lok = nekretnina.getLokacija();
		Objavio objavio = objavioRepository.findByNekretnina(nekretnina);
		Oglas oglas = oglasService.findByNekretnina(nekretnina);
			
		if(tehnicka != null) {
			Long idTehnicka = tehnicka.getId();
			
			nekretnina.setTehnickaOpremljenost(null);
			nekretnina = nekretninaService.save(nekretnina);
			tehnicakOpremljenostService.remove(idTehnicka);
			if(lok != null) {
				Long idLokacija = lok.getId();
				nekretnina.setLokacija(null);
				nekretnina = nekretninaService.save(nekretnina);
				lokacijaService.remove(idLokacija);
			} if(oglas != null) {
				Long idOglas = oglas.getId();
				oglasService.delete(idOglas);
				//objavio.setOglas(null);
				//objavioService.save(objavio);
				if(objavio != null) {
					Long idObjavio = objavio.getId();
					objavioService.remove(idObjavio);
					if(nekretnina != null){
						nekretninaService.remove(idNekretnina);
						return new ResponseEntity<>(HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
	}
	
}
