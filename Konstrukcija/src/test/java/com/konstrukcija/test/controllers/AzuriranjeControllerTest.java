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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.konstrukcija.test.konstante.AzuriranjeKonstante.OBLAST;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.ULICA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.BROJ_ULICE;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.BROJ_ZGRADE;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.BROJ_STANA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.GEO_DUZINA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.GEO_SIRINA;

import static com.konstrukcija.test.konstante.AzuriranjeKonstante.F_NAME;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.L_NAME;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.USERNAME;

import static com.konstrukcija.test.konstante.AzuriranjeKonstante.TERASA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.TELEFON;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.GARAZA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.KABLOVSKA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.POGLED_NA_GRAD;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.PODRUM;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.KAMIN;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.BAZEN;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.INTERNET;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.TAVAN;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.KLIMA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.LIFT;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.STRUJA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.POGLED_NA_MORE;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.VODA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.KANALIZACIJA;
import static com.konstrukcija.test.konstante.AzuriranjeKonstante.GAS;

import com.konstrukcija.App;
import com.konstrukcija.test.TestUtil;
import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.Lokacija;
import com.konstrukcija.model.TehnickaOpremljenost;
import com.konstrukcija.test.konstante.AzuriranjeKonstante;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")

public class AzuriranjeControllerTest {
private static final String URL_PREFIX = "/api/azuriranje";
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
    @Transactional
    @Rollback(true)
    public void updateLokacija() throws Exception {
        Lokacija lok = new Lokacija();
        lok.setOblas(OBLAST);
        lok.setUlica(ULICA);
        lok.setBroj_stana(BROJ_STANA);
        lok.setBroj_ulice(BROJ_ULICE);
        lok.setBroj_zgrade(BROJ_ZGRADE);
        
        lok.setGeo_duzina(GEO_DUZINA);
        lok.setGeo_sirina(GEO_SIRINA);

        String json = TestUtil.json(lok);
        mockMvc.perform(post(URL_PREFIX + "/update/lokacija/" + AzuriranjeKonstante.ID_LOKACIJE).contentType(contentType)
        		.param("ID_LOKACIJE", "1").content(json))
                .andExpect(status().isOk());
    }
	
	@Test
    @Transactional
    @Rollback(true)
    public void updateKorisnik() throws Exception {
        Korisnik user = new Korisnik();
        user.setFname(F_NAME);
        user.setFname(L_NAME);
        user.setFname(USERNAME);

        String json = TestUtil.json(user);
        mockMvc.perform(post(URL_PREFIX + "/update/").contentType(contentType).content(json)).andExpect(status().isOk());
    }
	
	@Test
    @Transactional
    @Rollback(true)
    public void getTehnickeNekretnine() throws Exception {
        TehnickaOpremljenost to = new TehnickaOpremljenost();
        to.setTarasa(TERASA);
        to.setTelefon(TELEFON);
        to.setGaraua(GARAZA);
        to.setKablovska(KABLOVSKA);
        to.setPogled_na_grad(POGLED_NA_GRAD);
        to.setPodrum(PODRUM);
        to.setKamin(KAMIN);
        to.setBazen(BAZEN);
        to.setInternet(INTERNET);
        to.setTavan(TAVAN);
        to.setKlima(KLIMA);
        to.setPogled_na_more(POGLED_NA_MORE);
        to.setLift(LIFT);
        to.setStrija(STRUJA);
        to.setVoda(VODA);
        to.setKanalizacija(KANALIZACIJA);
        to.setGas(GAS);

        String json = TestUtil.json(to);
        mockMvc.perform(post(URL_PREFIX + "/update/tehnicka/" + AzuriranjeKonstante.ID_TEHNICA).contentType(contentType)
        		.param("ID_TEHNICA", "2").content(json)).andExpect(status().isOk());
    }
}