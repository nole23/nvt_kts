package com.konstrukcija.controller;

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

import com.konstrukcija.dto.KorisnikDTO;
import com.konstrukcija.dto.LoginDTO;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.UserAuthority;
import com.konstrukcija.repository.AdminRepository;
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
	
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getKorisnik() {
		List<Korisnik> korisnik = korisnikServer.findAll();
		
		List<KorisnikDTO> korisnikDTO = new ArrayList<>();
		for(Korisnik k : korisnik) {
			korisnikDTO.add(new KorisnikDTO(k));
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
	public ResponseEntity<String>  saveKorisnika(@PathVariable String uloga, @RequestBody KorisnikDTO korisnikDTO) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Korisnik korisnik = new Korisnik();
		UserAuthority userAuthority = new UserAuthority();
			
		if(uloga.equals("korisnik")) {
			korisnik.setFname(korisnikDTO.getFname());
			korisnik.setLname(korisnikDTO.getLname());
			korisnik.setPassword(encoder.encode(korisnikDTO.getPassword()));
			korisnik.setEmail(korisnikDTO.getEmail());
			korisnik.setUsername(korisnikDTO.getUsername());
			korisnik.setVerified(false);
			korisnik.setVerifyCode(UUID.randomUUID().toString());
			
			
			if( korisnikServer.findByUsername(korisnikDTO.getUsername()) != null || korisnikServer.findByEmail(korisnikDTO.getEmail()) != null) {
				return new ResponseEntity<>("Ovaj email ili korisnicko ime je vec zauzeto", HttpStatus.BAD_REQUEST);
			}
			
			korisnik = korisnikServer.save(korisnik);
			userAuthoritRepository.save(userAuthority);
			mailSender.sendMail(korisnik.getEmail(), "Registration", "Click her to finish registration: <a href='http://localhost:8080/api/users/verify/"+korisnik.getVerifyCode()+"'>Click</a>");
			return new ResponseEntity<>("Uspesno ste se registrovali", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Cant create that type of user, ony Customer and Advertiser allowed",HttpStatus.BAD_REQUEST);
		}
		
		
	
	}
	
	/**
	 * Logovanje korisnika
	 * @param loginDTO
	 * @return kada se korisnik uloguje kreira se token koji govori koji je korisnik ulogova
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String>  login(@RequestBody LoginDTO loginDTO) {
		try{
			//provera korisnickom imena i pasvorda
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
			Authentication authentication = authenticationMenager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
			
			return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<String>("da li ovo radi "+loginDTO.getUsername(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Verifikacija profila
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping(value="/verify/{verifyCode}",method=RequestMethod.GET)
	public ResponseEntity<String> verify(@PathVariable String verifyCode){
		
		Korisnik korisnik = korisnikServer.findByVerifyCode(verifyCode);
		if(korisnik!=null){
			korisnik.setVerified(true);
			korisnikServer.save(korisnik);
		}
		
		return new ResponseEntity<>("redirect:#/" ,HttpStatus.OK);
	}
	
}
