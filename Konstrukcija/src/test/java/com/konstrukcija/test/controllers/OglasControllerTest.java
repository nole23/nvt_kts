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
import com.konstrukcija.TestUtil;
import com.konstrukcija.model.Oglas;
import com.konstrukcija.test.konstante.OglasKonstante;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")

public class OglasControllerTest {
	
private static final String URL_PREFIX = "/api/oglas";
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webAPplicationContext;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAPplicationContext).build();
	}
	
	@Test
	public void testGetAnOglas() throws Exception {
		this.mockMvc
		.perform(get(URL_PREFIX + "/" + OglasKonstante.OGLAS_ID).contentType(contentType)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(OglasKonstante.OGLAS_ID));
		
		this.mockMvc
		.perform(get(URL_PREFIX + "/" + 1564).contentType(contentType)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetAllOglas() throws Exception {
		this.mockMvc
		.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
			    .andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}

}
