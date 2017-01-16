package com.konstrukcija.test.controllers;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.konstrukcija.test.konstante.KorisnikKonstante.FIRST_NAME_THIRD;
import static com.konstrukcija.test.konstante.KorisnikKonstante.PASSWORD_THIRD;
import static com.konstrukcija.test.konstante.KorisnikKonstante.USERNAME;
import static com.konstrukcija.test.konstante.KorisnikKonstante.F_NAME;
import static com.konstrukcija.test.konstante.KorisnikKonstante.L_NAME;
import static com.konstrukcija.test.konstante.KorisnikKonstante.EMAIL;
import static com.konstrukcija.test.konstante.KorisnikKonstante.PASSWORD;
import static com.konstrukcija.test.konstante.KorisnikKonstante.USERNAME_SECOND;
import static com.konstrukcija.test.konstante.KorisnikKonstante.PASSWORD_SECOND;


import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.konstrukcija.App;
import com.konstrukcija.dto.LoginDTO;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.service.KorisnikService;
import com.konstrukcija.test.TestUtil;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class KorisnikControllerTest {
	
	private static final String URL_PREFIX = "/api/korisnik";
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webAPplicationContext;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAPplicationContext).build();
	}
	
	/**
	 * Metoda koja testira iscitavanje svih korisnika iz baze
	 * @throws Exception
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testGetAllKorisnici() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/all"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$.[*].fname").value(hasItem("Novica")))
			.andExpect(jsonPath("$.[*].lname").value(hasItem("Nikolic")))
			.andExpect(jsonPath("$.[*].email").value(hasItem("nole0223@gmail.com")));
	}
	
	/**
	 * Testiranje dva korisnika koja imaju isti email, test ne bi trebao da prodje.
	 * zato sto je zabranjeno da korisnici imaju isti email
	 * @throws Exception
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testSaveKorisnik() throws Exception {
		Korisnik korisnik = new Korisnik();
		korisnik.setFname(F_NAME);
		korisnik.setLname(L_NAME);
		korisnik.setEmail(EMAIL);
		korisnik.setPassword(PASSWORD);
		korisnik.setUsername(USERNAME);
		
		String json = TestUtil.json(korisnik);
		mockMvc.perform(post(URL_PREFIX+"/registration/korisnik").contentType(contentType).content(json)).andExpect(status().isCreated());
		
		
		korisnik = new Korisnik();
		korisnik.setFname(F_NAME);
		korisnik.setLname(L_NAME);
		korisnik.setEmail(EMAIL);
		korisnik.setPassword(PASSWORD_SECOND);
		korisnik.setUsername(USERNAME_SECOND);
		
		json = TestUtil.json(korisnik);
		mockMvc.perform(post(URL_PREFIX+"/registration/korisnik").contentType(contentType).content(json)).andExpect(status().isCreated());
		
	}

	/**
	 * Testiranje metode za logovanje korisnika, prvo se logujemo sa ispravnim podacima
	 * posle se logijemo sa neisptavnim podacima
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testLogin() throws Exception {
		LoginDTO login = new LoginDTO();
		login.setUsername(USERNAME_SECOND);
		login.setPassword(PASSWORD_SECOND);

		String json = TestUtil.json(login);
		mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json)).andExpect(status().isOk());

		login.setUsername(FIRST_NAME_THIRD);
		login.setPassword(PASSWORD_THIRD);

		json = TestUtil.json(login);
		mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json))
				.andExpect(status().isNotFound());
	}
	
	
	
	/**
	 * Testiranje metode koja proverava verifikacioni kod kako bi bilo zavrseno registrovanje korisnika
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void TestVerify() throws Exception {
		Korisnik korisnik = korisnikService.findByUsername(USERNAME_SECOND);
		mockMvc.perform(get(URL_PREFIX + "/verify/" + korisnik.getVerifyCode())).andExpect(status().isOk());

		mockMvc.perform(get(URL_PREFIX + "/verify/" + korisnik.getVerifyCode() + "asssss"))
				.andExpect(status().isNotFound());
	}
}
