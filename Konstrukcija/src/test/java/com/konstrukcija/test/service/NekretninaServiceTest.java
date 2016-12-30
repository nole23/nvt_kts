package com.konstrukcija.test.service;

import static com.konstrukcija.test.konstante.KorisnikKonstante.DB_COUNT;
import static com.konstrukcija.test.konstante.NekretninaKonstante.NAZIV_NEKRETNINE;
import static com.konstrukcija.test.konstante.NekretninaKonstante.CENA;
import static com.konstrukcija.test.konstante.NekretninaKonstante.POVRSINA;
import static com.konstrukcija.test.konstante.NekretninaKonstante.SOBRNOS;
import static com.konstrukcija.test.konstante.NekretninaKonstante.STANJE_OBJEKTA;
import static com.konstrukcija.test.konstante.NekretninaKonstante.GREJANJE;
import static com.konstrukcija.test.konstante.NekretninaKonstante.SPRATOVA;
import static com.konstrukcija.test.konstante.NekretninaKonstante.SPRAT;
import static com.konstrukcija.test.konstante.NekretninaKonstante.OPIS;
import static org.assertj.core.api.Assertions.assertThat;

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

import com.konstrukcija.App;
import com.konstrukcija.model.Nekretnina;
import com.konstrukcija.service.NekretnineService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class NekretninaServiceTest {

	@Autowired
	private NekretnineService nekretninaService;
	

	/**
	 * Testiranje metode findAll iz NekretnineService
	 * Iscitavanje svih nekretnina
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testFindAll(){
		List<Nekretnina> nekretnina = nekretninaService.findAll();
		assertThat(nekretnina).hasSize(DB_COUNT);
	}
	
	/**
	 * Ova metoda testira save iz NekretninaService. Cuva podatke o nekretnine
	 * koji se nalaze u NekretnineKonstante i proverava da li je page size 
	 * uvecan za jedan
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testAdd(){
		Nekretnina nekretnina = new Nekretnina();
		nekretnina.setNaziv_nekretnine(NAZIV_NEKRETNINE);
		nekretnina.setCena(CENA);
		nekretnina.setPovrsina(POVRSINA);
		nekretnina.setSobnost(SOBRNOS);
		nekretnina.setStanje_objekta(STANJE_OBJEKTA);
		nekretnina.setGrejanje(GREJANJE);
		nekretnina.setSpratova(SPRATOVA);
		nekretnina.setSprat(SPRAT);
		nekretnina.setOpis(OPIS);
		
		int previusSize = nekretninaService.findAll().size();
		Nekretnina addNekrenina = nekretninaService.save(nekretnina);
		assertThat(addNekrenina).isNotNull();
		assertThat(nekretninaService.findAll()).hasSize(previusSize+1);
		
		assertThat(addNekrenina.getNaziv_nekretnine()).isEqualTo(NAZIV_NEKRETNINE);
		assertThat(addNekrenina.getCena()).isEqualTo(CENA);
		assertThat(addNekrenina.getPovrsina()).isEqualTo(POVRSINA);
		assertThat(addNekrenina.getSobnost()).isEqualTo(SOBRNOS);
		assertThat(addNekrenina.getStanje_objekta()).isEqualTo(STANJE_OBJEKTA);
		assertThat(addNekrenina.getGrejanje()).isEqualTo(GREJANJE);
		assertThat(addNekrenina.getSpratova()).isEqualTo(SPRATOVA);
		assertThat(addNekrenina.getSprat()).isEqualTo(SPRAT);
		assertThat(addNekrenina.getOpis()).isEqualTo(OPIS);
	}
	
	/**
	 * Metoda koja testira brisanje nekretnine iz NekretninaService
	 * Prvo sacuvano novu nekrtninu pa je obrisemo to proverimo sa prevSize
	 * da li ce se desiti razlika 
	 * 
	 * @author Novica Nikolic
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testRemove(){
		Nekretnina nekretnina = new Nekretnina();
		nekretnina.setNaziv_nekretnine(NAZIV_NEKRETNINE);
		nekretnina.setCena(CENA);
		nekretnina.setPovrsina(POVRSINA);
		nekretnina.setSobnost(SOBRNOS);
		nekretnina.setStanje_objekta(STANJE_OBJEKTA);
		nekretnina.setGrejanje(GREJANJE);
		nekretnina.setSpratova(SPRATOVA);
		nekretnina.setSprat(SPRAT);
		nekretnina.setOpis(OPIS);
		
		nekretnina = nekretninaService.save(nekretnina);
		int prevSize = nekretninaService.findAll().size();
		nekretninaService.remove(nekretnina.getId());
		assertThat(nekretninaService.findAll()).hasSize(prevSize-1);
		
		Nekretnina nek = nekretninaService.findOne(nekretnina.getId());
		assertThat(nek).isNull();
	}
}
