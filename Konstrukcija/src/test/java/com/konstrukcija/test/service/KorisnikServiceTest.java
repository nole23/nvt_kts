package com.konstrukcija.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static com.konstrukcija.test.konstante.KorisnikKonstante.DB_COUNT;
import static com.konstrukcija.test.konstante.KorisnikKonstante.ADMIN_ID;
import static com.konstrukcija.test.konstante.KorisnikKonstante.ADMIN_USERNAME;
import static com.konstrukcija.test.konstante.KorisnikKonstante.ADMIN_EMAIL;
import static com.konstrukcija.test.konstante.KorisnikKonstante.ADMIN_F_NAME;
import static com.konstrukcija.test.konstante.KorisnikKonstante.ADMIN_L_NAME;
import static com.konstrukcija.test.konstante.KorisnikKonstante.USERNAME;
import static com.konstrukcija.test.konstante.KorisnikKonstante.F_NAME;
import static com.konstrukcija.test.konstante.KorisnikKonstante.L_NAME;
import static com.konstrukcija.test.konstante.KorisnikKonstante.EMAIL;
import static com.konstrukcija.test.konstante.KorisnikKonstante.PASSWORD;
import static com.konstrukcija.test.konstante.KorisnikKonstante.VERIFIED;
import static com.konstrukcija.test.konstante.KorisnikKonstante.USERNAME_SECOND;
import static com.konstrukcija.test.konstante.KorisnikKonstante.PASSWORD_SECOND;
import static com.konstrukcija.test.konstante.KorisnikKonstante.EMAIL_SECOND;
import static com.konstrukcija.test.konstante.KorisnikKonstante.VERIFY_CODE;

import com.konstrukcija.App;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.service.KorisnikService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class KorisnikServiceTest {
	
	@Autowired
	private KorisnikService korisnikService;
	

	/**
	 * Testiranje metode findAll iz KorisnikService
	 * Izvrsava se metoda i uporedjuje sa DB_COUNT
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testFindAll(){
		List<Korisnik> korisnici = korisnikService.findAll();
		assertThat(korisnici).hasSize(DB_COUNT);
	}
	
	/**
	 * Testiranje metode findOne koja treba da nadje jednog admina
	 * na osnovu ADMIN_ID koji se nalazi u KorisnikKonstante
	 * Ova metoda vraca podatke o adminu
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testFindOne(){
		Korisnik korisnik = korisnikService.findOne(ADMIN_ID);
		assertThat(korisnik.getUsername()).isEqualTo(ADMIN_USERNAME);
		assertThat(korisnik.getEmail()).isEqualTo(ADMIN_EMAIL);
		assertThat(korisnik.getFname()).isEqualTo(ADMIN_F_NAME);
		assertThat(korisnik.getLname()).isEqualTo(ADMIN_L_NAME);
		
	}
	

	/**
	 * Ova metoda testira save iz KorisnikService. Cuva podatke o korisniku
	 * koji se nalaze u KorisnikKonstante i proverava da li je page size 
	 * uvecan za jedan
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testAdd(){
		Korisnik korisnik = new Korisnik();
		korisnik.setUsername(USERNAME);
		korisnik.setFname(F_NAME);
		korisnik.setLname(L_NAME);
		korisnik.setEmail(EMAIL);
		korisnik.setPassword(PASSWORD);
		korisnik.setVerified(VERIFIED);
		
		int previusSize = korisnikService.findAll().size();
		Korisnik addKorisnik = korisnikService.save(korisnik);
		assertThat(addKorisnik).isNotNull();
		assertThat(korisnikService.findAll()).hasSize(previusSize+1);
		
		assertThat(addKorisnik.getUsername()).isEqualTo(USERNAME);
		assertThat(addKorisnik.getEmail()).isEqualTo(EMAIL);
		assertThat(addKorisnik.getFname()).isEqualTo(F_NAME);
		assertThat(addKorisnik.getLname()).isEqualTo(L_NAME);
		assertThat(addKorisnik.getVerified()).isEqualTo(VERIFIED);
		
	}
	

	/**
	 * Metoda koja testira logovanje korisnika na osnovu username i password
	 * Podaci koji se proveravaju u bazi nalaze se u KorisnikKonstante
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testFindOneByUsernameAndPassword(){
		Korisnik korisnik = korisnikService.findOneByUsernameAndPassword(USERNAME_SECOND,PASSWORD_SECOND);
		
		assertThat(korisnik).isNotNull();
		assertThat(korisnik.getUsername()).isEqualTo(USERNAME_SECOND);
		assertThat(korisnik.getPassword()).isEqualTo(PASSWORD_SECOND);
	}

	/**
	 * Testiranje metode findByUsername iz KorisnikService. Ovaj test
	 * ppokusace da pronadje korisnika sa datim username u bazu. USERNAME_SECOND
	 * se nalazi u KorisnikKonstante
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testFindByUsername(){
		Korisnik korisnik = korisnikService.findByUsername(USERNAME_SECOND);
		
		assertThat(korisnik).isNotNull();
		assertThat(korisnik.getUsername()).isEqualTo(USERNAME_SECOND);
	}

	/**
	 * Testiranje metode findByEmail iz KorisnikService. Ovaj test radi slicno
	 * kao i testiranje findByUsername. Ova metoda pokusava da pronadje 
	 * korisnika sa datim email
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testFindByEmail(){
		Korisnik korisnik = korisnikService.findByEmail(EMAIL_SECOND);
		
		assertThat(korisnik).isNotNull();
		assertThat(korisnik.getEmail()).isEqualTo(EMAIL_SECOND);
	}
	
	/**
	 * Ova metoda pokusava da pronadje VerifyCode na osnovu VerifyCode koji se nalazi u 
	 * KorisnikKonstante
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void TestFindByVerifyCode(){
		Korisnik korisnik = korisnikService.findByVerifyCode(VERIFY_CODE);
		
		assertThat(korisnik).isNotNull();
		assertThat(korisnik.getVerifyCode()).isEqualTo(VERIFY_CODE);
	}
	
	

}
