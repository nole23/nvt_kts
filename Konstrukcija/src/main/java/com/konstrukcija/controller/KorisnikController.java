package com.konstrukcija.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konstrukcija.dto.AdresaDTO;
import com.konstrukcija.dto.KorisniciListaDTO;
import com.konstrukcija.dto.KorisnikDTO;
import com.konstrukcija.dto.LoginDTO;
import com.konstrukcija.dto.MessageDTO;
import com.konstrukcija.model.Admin;
import com.konstrukcija.model.Adresa;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.UserAuthority;
import com.konstrukcija.repository.AdminRepository;
import com.konstrukcija.repository.AdresaRepository;
import com.konstrukcija.repository.UserAuthorityRepository;
import com.konstrukcija.security.TokenUtils;
import com.konstrukcija.service.KorisnikService;
import com.konstrukcija.service.MyMailSenderService;

/**
 * 
 * @author Novica Nikolic
 * 
 * Ovaj kontroler sluzi za registraciju novog korisnika kao i logovanje korisnika
 * Ulazni parametri su osnovni podaci o korisniku a izlazni su ili dodat novi korisnik 
 * ili uspesno logovanje
 *
 */


@RestController
@RequestMapping(value="api/users")
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikServer;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserAuthorityRepository userAuthoritRepository;
	
	@Autowired
	AuthenticationManager authenticationMenager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	private MyMailSenderService mailSender;
	
	@Autowired
	private AdresaRepository adresaRepository;
	
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getKorisnik() {
		List<Korisnik> korisnik = korisnikServer.findAll();
		
		List<KorisnikDTO> korisnikDTO = new ArrayList<>();
		for(Korisnik k : korisnik) {
			if(k.isActive() == true)
				korisnikDTO.add(new KorisnikDTO(k));
		}
		return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/all/users",method = RequestMethod.GET)
	public ResponseEntity<List<KorisniciListaDTO>> getKorisnikAll() {
		List<Korisnik> korisnik = korisnikServer.findAll();
		
		List<KorisniciListaDTO> korisnikDTO = new ArrayList<>();
		for(Korisnik k : korisnik) {
			if(k.isActive() == true)
				korisnikDTO.add(new KorisniciListaDTO(k));
		}
		return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
	}

	/**
	 * 
	 * Registracija korisnika
	 * @param uloga, uloga je uvek korisnik
	 * @param korisnikDTO
	 * @return moguce je registrovati samo korisika, kasnije ako je potrebmo mozemo korisnika kreirati kao admina
	 * ili zaposlenog
	 */
	@RequestMapping(value="/registration/{uloga}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageDTO>  saveKorisnika(@PathVariable String uloga, @RequestBody KorisnikDTO korisnikDTO) {
		
		MessageDTO messageDTO = new MessageDTO();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Korisnik korisnik = new Korisnik();
		UserAuthority userAuthority = new UserAuthority();
		
		if(uloga.equals("korisnik")) {
			
			if( korisnikServer.findByUsername(korisnikDTO.getUsername()) != null) {
				messageDTO.setError("usernamZauzet");
				return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
			}
			if( korisnikServer.findByEmail(korisnikDTO.getEmail()) != null ) {
				messageDTO.setError("mailZauzet");
				return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
			}
			
			
			korisnik.setFname(korisnikDTO.getFname());
			korisnik.setLname(korisnikDTO.getLname());
			korisnik.setPassword(encoder.encode(korisnikDTO.getPassword()));
			korisnik.setEmail(korisnikDTO.getEmail());
			korisnik.setUsername(korisnikDTO.getUsername());
			korisnik.setActive(true);
			korisnik.setVerified(false);
			korisnik.setVerifyCode(UUID.randomUUID().toString());
			
			Admin admin = adminRepository.findByName(uloga);
			
			userAuthority.setAdmin(admin);
			userAuthority.setKorisnik(korisnik);
			
			
			
			korisnik = korisnikServer.save(korisnik);
			userAuthoritRepository.save(userAuthority);
			mailSender.sendMail(korisnik.getEmail(), "Registration", "Click her to finish registration: <a href='http://localhost:8080/api/users/verify/"+korisnik.getVerifyCode()+"'>Click</a>");
			messageDTO.setSuccess("uspesno");
			return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
		} else {
			messageDTO.setError("error");
			return new ResponseEntity<MessageDTO>(messageDTO,HttpStatus.OK);
		}
		
		
	
	}
	
	/**
	 * Logovanje korisnika
	 * @param loginDTO
	 * @return kada se korisnik uloguje kreira se token koji govori koji je korisnik ulogova
	 */
	@RequestMapping(value="/login/", method = RequestMethod.POST)
	public ResponseEntity<MessageDTO> loginKorisnik(@RequestBody LoginDTO loginDTO) {
		MessageDTO messageDTO = new MessageDTO();
		Korisnik korisnik = korisnikServer.findByUsername(loginDTO.getUsername());
		if(korisnik == null) {
			messageDTO.setError("ime");
			return new ResponseEntity<>(messageDTO, HttpStatus.OK);
		}
		if(korisnik.isActive() == false) {
			messageDTO.setError("active");
			return new ResponseEntity<>(messageDTO, HttpStatus.OK);
		}
		if(korisnik.getVerified() == false) {
			messageDTO.setError("verified");
			return new ResponseEntity<>(messageDTO, HttpStatus.OK);
		}
		
		try{
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
			Authentication authentication = authenticationMenager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
			
			
			
			messageDTO.setCookies(tokenUtils.generateToken(details));
			messageDTO.setRola(korisnik.getUserAuthorities().getAdmin().getName());
			return new ResponseEntity<>(messageDTO, HttpStatus.OK);
		} catch(Exception ex) {
			messageDTO.setError("sifra");
			return new ResponseEntity<>(messageDTO, HttpStatus.OK);
		}
	}
	
	/**
	 * Verifikacija profila
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping(value="/verify/{verifyCode}",method=RequestMethod.GET)
	public ResponseEntity<MessageDTO> verify(@PathVariable String verifyCode){
		
		Korisnik korisnik = korisnikServer.findByVerifyCode(verifyCode);
		if(korisnik!=null){
			korisnik.setVerified(true);
			korisnikServer.save(korisnik);
		}
		return new ResponseEntity<MessageDTO>(HttpStatus.OK);
	}
	
	
	/**
	 * @param korisnikDTO korisnik koji vise nije admin
	 * @param principal provera da li je ulogovan i da li je admin
	 * @return Aktuelni admin skida administraturu postojecem adminu
	 */
	@RequestMapping(value="/removal/admin",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> removalAdmin(@RequestBody KorisnikDTO korisnikDTO, Principal principal){

		Korisnik admin = korisnikServer.findByUsername(principal.getName());
		if(admin.getUserAuthorities().getAdmin().getName().equals("admin"))
			return new ResponseEntity<>("Ovu komandu samo admin moze da koristi", HttpStatus.BAD_REQUEST);
		
		Korisnik korisnik = korisnikServer.findByUsername(korisnikDTO.getUsername());
		UserAuthority userAuthority = userAuthoritRepository.findByKorisnik(korisnik);
		String id = "2";//Prvo admin pa korisnik
		Admin admin1 = adminRepository.findOne(Long.parseLong(id));
		userAuthority.setAdmin(admin1);
		
		userAuthoritRepository.save(userAuthority);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param principal korisnik koji je prijavljen
	 * @return logicko brisanje korisnika koji je prijavljen
	 */
	@RequestMapping(value="/removal/korisnik",method=RequestMethod.GET)
	public ResponseEntity<String> removalKorisnik(Principal principal){

		Korisnik korisnik = korisnikServer.findByUsername(principal.getName());
		if(korisnik == null)
			return new ResponseEntity<>("Niste ulogovani", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		
		korisnik.setActive(false);
		
		korisnikServer.save(korisnik);
		
		return new ResponseEntity<>("redirect:api/users/logout",HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param principal
	 * @return logout korisnik
	 */
	@RequestMapping(value="/logout",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> logout(Principal principal){

		Korisnik korisnik = korisnikServer.findByUsername(principal.getName());
		if(korisnik != null)
			return new ResponseEntity<>("redirect:#/login", HttpStatus.OK);
		
		
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * @param principal korisnik kog zelimo azurirati
	 * @param korisnikDTO novi podatak
	 * @return azuriranje emaila
	 */
	@RequestMapping(value="/updat/email",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> updatEmail(Principal principal, @RequestBody KorisnikDTO korisnikDTO){

		Korisnik korisnik = korisnikServer.findByUsername(principal.getName());
		if(korisnik == null)
			return new ResponseEntity<>("Niste ulogovani",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		
		korisnik.setEmail(korisnikDTO.getEmail());
		korisnik.setVerified(false);
		korisnik.setVerifyCode(UUID.randomUUID().toString());
		
		korisnik = korisnikServer.save(korisnik);
		mailSender.sendMail(korisnik.getEmail(), "Registration", "Click her to finish registration: <a href='http://localhost:8080/api/users/verify/"+korisnik.getVerifyCode()+"'>Click</a>");
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * @param principal korisnik kog azuriramo
	 * @param korisnikDTO podatak koji menjamo
	 * @return azuriranje sifre korisnika
	 */
	@RequestMapping(value="/updat/password",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> updatPassword(Principal principal, @RequestBody KorisnikDTO korisnikDTO){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Korisnik korisnik = korisnikServer.findByUsername(principal.getName());
		if(korisnik == null)
			return new ResponseEntity<>("Niste ulogovani",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		
		korisnik.setPassword(encoder.encode(korisnikDTO.getPassword()));
		korisnik.setVerified(false);
		korisnik.setVerifyCode(UUID.randomUUID().toString());
		
		korisnik = korisnikServer.save(korisnik);
		mailSender.sendMail(korisnik.getEmail(), "Registration", "Click her to finish registration: <a href='http://localhost:8080/api/users/verify/"+korisnik.getVerifyCode()+"'>Click</a>");
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@RequestMapping(value="/active",method=RequestMethod.GET)
	public ResponseEntity<String> active(Principal principal){

		Korisnik korisnik = korisnikServer.findByUsername(principal.getName());
		
		korisnik.setActive(true);
		
		korisnik = korisnikServer.save(korisnik);
		
		return new ResponseEntity<>("Ponovo ste aktivirali nalog",HttpStatus.OK);
	}
	
	@RequestMapping(value="/profile",method=RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> getUsers(Principal principal){

		Korisnik korisnik = korisnikServer.findByUsername(principal.getName());
		
		return new ResponseEntity<>(new KorisnikDTO(korisnik), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/adresa", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageDTO> getLokacijaNekretnine(Principal principal, @RequestBody AdresaDTO adresaDTO) {
		
		MessageDTO mesageDTO = new MessageDTO();
		Korisnik korisnik = korisnikServer.findByUsername(principal.getName());
		Adresa adresa = new Adresa();
		
		adresa.setDrzava(adresaDTO.getDrzava());
		adresa.setGrad(adresaDTO.getGrad());
		adresa.setUlica(adresaDTO.getUlica());
		adresa.setBroj_zgrade(adresaDTO.getBroj_zgrade());
		adresa.setBroj_stama(adresaDTO.getBroj_stama());
		
		korisnik.setAdresa(adresa);
		
		adresaRepository.save(adresa);
		korisnikServer.save(korisnik);
		mesageDTO.setSuccess("azuriranaAdresa");
		return new ResponseEntity<MessageDTO>(mesageDTO, HttpStatus.OK);
	}
}
