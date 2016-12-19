package com.konstrukcija.controller;

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

import com.konstrukcija.dto.KorisnikDTO;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.UserAuthority;
import com.konstrukcija.repository.AdminRepository;
import com.konstrukcija.repository.UserAuthorityRepository;
import com.konstrukcija.service.KorisnikService;

@RestController
@RequestMapping(value="api/users")
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikServer;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserAuthorityRepository userAuthoritRepository;
	
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getKorisnik() {
		List<Korisnik> korisnik = korisnikServer.findAll();
		
		List<KorisnikDTO> korisnikDTO = new ArrayList<>();
		for(Korisnik k : korisnik) {
			korisnikDTO.add(new KorisnikDTO(k));
		}
		return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
	}
	

	
	//Registracija novog korisnika
	//Posle treba dodati jos i registracija ostalih korisnika sajta kao sto su admin, kupac, menadzer, radnik u kompaniji
	@RequestMapping(value="/registration/{userType}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String>  saveKorisnika(@PathVariable String  userType, @RequestBody KorisnikDTO korisnikDTO) {
		Korisnik korisnik;
		UserAuthority uloga = new UserAuthority();
		if(userType.equalsIgnoreCase("kupac")) {
			korisnik = new Korisnik();
			uloga.setAdmin(adminRepository.findByName(("KUPAC")));
			uloga.setKorisnik(korisnik);
			
		} 
		//treba implementirati i registraciju ostalih korisnika sajta(admin, radni u kompaniji, menadzer ...)
		else {
			return new ResponseEntity<>("Nije moguce dodati novog korisnika", HttpStatus.BAD_REQUEST);
		}
		korisnik.setFname(korisnikDTO.getFname());
		korisnik.setLname(korisnikDTO.getFname());
		korisnik.setEmail(korisnikDTO.getFname());
		korisnik.setPass(korisnikDTO.getFname());
		korisnik.setPhone_number(korisnikDTO.getFname());
		korisnik.setAdresa(korisnikDTO.getAdresa());
		
		if(korisnikServer.findByEmail(korisnik.getEmail()) != null) {
			return new ResponseEntity<>("Ovaj email je zauzet proverite vas email", HttpStatus.BAD_REQUEST);
		}
		korisnik = korisnikServer.save(korisnik);
		userAuthoritRepository.save(uloga);
		//Treba kreirati slanje email
		return new ResponseEntity<>("Uspesno ste se registrovali", HttpStatus.CREATED);
		
	}
}
