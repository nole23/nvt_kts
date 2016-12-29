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

import com.konstrukcija.dto.LokacijaDTO;
import com.konstrukcija.dto.NekretninaDTO;
import com.konstrukcija.dto.TehnickaOpremljenostDTO;
import com.konstrukcija.model.Kategorija;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Lokacija;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.model.Objavio;
import com.konstrukcija.model.TehnickaOpremljenost;
import com.konstrukcija.repository.KategorijaRepository;
import com.konstrukcija.repository.KompanijaRepository;
import com.konstrukcija.repository.KorisnikRepository;
import com.konstrukcija.repository.LokacijaRepository;
import com.konstrukcija.repository.ObjavioRepository;
import com.konstrukcija.service.KorisnikService;
import com.konstrukcija.service.LokacijaService;
import com.konstrukcija.service.NekretnineService;
import com.konstrukcija.service.TehnickaOpremljenostService;

/**
 * 
 * @author X
 * Dodavanje nove nekretnine
 * @param vrstaNekretnine, idKorisnik, idKopanija
 * @return sacuvan u bazi nova nekretnina u zavisnosti ko ju je kreirao.
 */
@RestController
@RequestMapping(value = "api/nekretnineee")
public class NekretnineController {
	
	@Autowired
	private NekretnineService nekretninaService;
	
	@Autowired
	private KategorijaRepository kategorijaRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private KompanijaRepository kompanijaRepository;
	
	@Autowired
	private ObjavioRepository objavioRepository;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private LokacijaService lokacijaService;
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	@Autowired
	private TehnickaOpremljenostService tehnickaOpremljenostService;

	//Ispis svih nekretnina
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<NekretninaDTO>> getAllNekretnina() {
		

		List<Nekretnina> nekrenine = nekretninaService.findAll();
		
		List<NekretninaDTO> nekretnineDTO = new ArrayList<>();
		for (Nekretnina n : nekrenine) {
			if(n.getLokacija()!= null || n.getTehnickaOpremljenost() != null)  
				if(n.getLokacija() != null)
					if(n.getTehnickaOpremljenost() != null)
						nekretnineDTO.add(new NekretninaDTO(n));
		}
		return new ResponseEntity<>(nekretnineDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Test slucaj proveriti sa asistentom
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Korisnik> gethahah(Principal principal) {
		Korisnik k = korisnikService.findByUsername(principal.getName());

		return new ResponseEntity<>(k, HttpStatus.OK);
	}
	
	

	/**
	 * 
	 * Ova metoda treba da proveri da li je korisnik ili kompanija dodaje nekretninu, takodje proverava da li je se nekretnina izdaje ili prodaje
	 * 
	 * @param nazivKat, kategorija oglasa (prodaja, izdavanje)
	 * @param idKompanija, id kompanije 
	 * @param idKorisnik, id korisnika 
	 * 
	 * NAPOMENA: idKompanija i idKorisnik ne mogu zajedno moraju eksplicitno idKorisnika ili idKompanije da se proslede
	 * 
	 * @param nekretninaDTO, podaci nekretnine
	 * @return saveNekretnina 
	 */
	@RequestMapping(value = "/add/{idKategorija}/{idKorisnik}/{idKompanija}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> saveNekretnine(@PathVariable Long idKategorija, @PathVariable String idKompanija, @PathVariable String idKorisnik, @RequestBody NekretninaDTO nekretninaDTO) {
		Kategorija kat = kategorijaRepository.findOne(idKategorija);
		String nazivKat = kat.getTip();
		
		if(idKompanija.equals("null")){
			
			Long id = Long.parseLong(idKorisnik);
			
			
			
			if(nazivKat.equals("prodaja")) {
				
				Nekretnina nekretnina;			
				Objavio objavio = new Objavio();
				
				
				nekretnina = new Nekretnina();
				
				nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
				nekretnina.setCena(nekretninaDTO.getCena());
				nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
				nekretnina.setSobnost(nekretninaDTO.getSobnost());
				nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
				nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
				nekretnina.setSpratova(nekretninaDTO.getSpratova());
				nekretnina.setStanje(nekretninaDTO.getStanje());
				nekretnina.setSprat(nekretninaDTO.getSprat());
				nekretnina.setOpis(nekretninaDTO.getOpis());
				nekretnina.setKategorija(kategorijaRepository.findOne(idKategorija));
				
				objavio.setKorisnik(korisnikRepository.findOne(id));
				objavio.setKompanija(null);
				objavio.setNekretnina(nekretnina);
				
				nekretnina = nekretninaService.save(nekretnina);
				objavioRepository.save(objavio);
				
				return new ResponseEntity<>("korisnik "+id,HttpStatus.OK);
			
			} else {
				Nekretnina nekretnina;
				Objavio objavio = new Objavio();
				
				
				nekretnina = new Nekretnina();
				
				nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
				nekretnina.setCena(nekretninaDTO.getCena());
				nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
				nekretnina.setSobnost(nekretninaDTO.getSobnost());
				nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
				nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
				nekretnina.setSpratova(nekretninaDTO.getSpratova());
				nekretnina.setStanje(nekretninaDTO.getStanje());
				nekretnina.setSprat(nekretninaDTO.getSprat());
				nekretnina.setOpis(nekretninaDTO.getOpis());
				nekretnina.setKategorija(kategorijaRepository.findOne(idKategorija));
				
				objavio.setKorisnik(korisnikRepository.findOne(id));
				objavio.setKompanija(null);
				objavio.setNekretnina(nekretnina);
				
				nekretnina = nekretninaService.save(nekretnina);
				objavioRepository.save(objavio);
				
				return new ResponseEntity<>("korisnik "+id,HttpStatus.OK);
			}
		} else if(idKorisnik.equals("null")) {
			
			Long id = Long.parseLong(idKompanija);
			
			if(nazivKat.equals("prodaja")) {
				
				Nekretnina nekretnina;
				Objavio objavio = new Objavio();
				
				
				nekretnina = new Nekretnina();
				
				nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
				nekretnina.setCena(nekretninaDTO.getCena());
				nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
				nekretnina.setSobnost(nekretninaDTO.getSobnost());
				nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
				nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
				nekretnina.setSpratova(nekretninaDTO.getSpratova());
				nekretnina.setStanje(nekretninaDTO.getStanje());
				nekretnina.setSprat(nekretninaDTO.getSprat());
				nekretnina.setOpis(nekretninaDTO.getOpis());
				nekretnina.setKategorija(kategorijaRepository.findOne(idKategorija));
				
				objavio.setKompanija(kompanijaRepository.findOne(id));
				objavio.setKorisnik(null);
				objavio.setNekretnina(nekretnina);
				
				nekretnina = nekretninaService.save(nekretnina);
				objavioRepository.save(objavio);
				
				return new ResponseEntity<>("korisnik "+id,HttpStatus.OK);
			
			} else {
				Nekretnina nekretnina;
				Objavio objavio = new Objavio();
				
				
				nekretnina = new Nekretnina();
				
				nekretnina.setNaziv_nekretnine(nekretninaDTO.getNaziv_nekretnine());
				nekretnina.setCena(nekretninaDTO.getCena());
				nekretnina.setPovrsina(nekretninaDTO.getPovrsina());
				nekretnina.setSobnost(nekretninaDTO.getSobnost());
				nekretnina.setStanje_objekta(nekretninaDTO.getStanje_objekta());
				nekretnina.setGrejanje(nekretninaDTO.getGrejanje());
				nekretnina.setSpratova(nekretninaDTO.getSpratova());
				nekretnina.setStanje(nekretninaDTO.getStanje());
				nekretnina.setSprat(nekretninaDTO.getSprat());
				nekretnina.setOpis(nekretninaDTO.getOpis());
				nekretnina.setKategorija(kategorijaRepository.findOne(idKategorija));
				
				objavio.setKompanija(kompanijaRepository.findOne(id));
				objavio.setKorisnik(null);
				objavio.setNekretnina(nekretnina);
				
				nekretnina = nekretninaService.save(nekretnina);
				objavioRepository.save(objavio);
				
				return new ResponseEntity<>("korisnik "+id,HttpStatus.OK);
			}
			
		} else  {
			return new ResponseEntity<String>("Lose",HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Dodavanje lokacije datoj nekretnini
	 * @param idNekretnina, id nekeretnine koju zelimo da azuriramo
	 * @param lokacijaDTO, podaci o lokaciji gde se nalazi i slicno
	 * @return sacuvana u bazi nova lokacija i azurirana nekretnina
	 */
	@RequestMapping(value = "/lokacija/{idNekretnina}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getLokacijaNekretnine(@PathVariable Long idNekretnina, @RequestBody LokacijaDTO lokacijaDTO) {
		
		Nekretnina nekretnina = nekretninaService.findOne(idNekretnina);
		Lokacija lokacija = new Lokacija();
		
		lokacija.setDrzava(lokacijaDTO.getDrzava());
		lokacija.setGrad(lokacijaDTO.getGrad());
		lokacija.setOblas(lokacijaDTO.getOblas());
		lokacija.setUlica(lokacijaDTO.getUlica());
		lokacija.setBroj_ulice(lokacijaDTO.getBroj_ulice());
		lokacija.setBroj_zgrade(lokacijaDTO.getBroj_zgrade());
		lokacija.setBroj_stana(lokacijaDTO.getBroj_stana());
		
		nekretnina.setLokacija(lokacija);
		
		lokacija = lokacijaService.save(lokacija);
		nekretninaService.save(nekretnina);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Dodavanje nekih boolean vrednosti o nekretnini (npr. Sta poseduje sve nekretnina)
	 * @param idNekretnina, id nekretnine koji azuriramo
	 * @param tehnickaOpremljenostDTO, podaci o koji se cuvaju u bazu
	 * @return na osnovu idNekretnine odredjenoj nekrtnini dodajemo tehnicku opreljenost
	 */
	@RequestMapping(value = "/tehnicka/opremljenost/{idNekretnina}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> getTehnickaNekretnine(@PathVariable Long idNekretnina, @RequestBody TehnickaOpremljenostDTO tehnickaOpremljenostDTO) {
		
		Nekretnina nekretnina = nekretninaService.findOne(idNekretnina);
		TehnickaOpremljenost tehnickaOpremljenost = new TehnickaOpremljenost();
		
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
		
		
		nekretnina.setTehnickaOpremljenost(tehnickaOpremljenost);
		
		tehnickaOpremljenost = tehnickaOpremljenostService.save(tehnickaOpremljenost);
		nekretninaService.save(nekretnina);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
