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

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.konstrukcija.App;
import com.konstrukcija.test.TestUtil;
import com.konstrukcija.model.Admin;
import com.konstrukcija.model.Kategorija;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.test.konstante.AdminKonstante;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")

public class AdminControllerTest {
private static final String URL_PREFIX = "/api/admin";
	
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
	
	/**
	@Test
	@Transactional
	@Rollback(true)
	public void testGetAllAdmins() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/all"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$.[*].name").value(hasItem("admin")));
	}
	**/
	
	@Test
    @Transactional
    @Rollback(true)
    public void NewKategorija() throws Exception {
        Kategorija kat = new Kategorija();
        kat.setName("Nova Kategorija");
        kat.setTip("Novi tip");

        String json = TestUtil.json(kat);
        mockMvc.perform(post(URL_PREFIX + "/add/kategorija/" + AdminKonstante.ADMIN_ID).contentType(contentType)
        		.param("ADMIN_ID", "1l").content(json))
                .andExpect(status().isOk());
    }
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetNekretnina() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/prijavljena/nekretnina"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$.[*].name").value(hasItem("kat")))
			.andExpect(jsonPath("$.[*].tip").value(hasItem("tip")));
	}
	
}
