package com.konstrukcija.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.konstrukcija.test.controllers.AdminControllerTest;
import com.konstrukcija.test.controllers.AzuriranjeControllerTest;
import com.konstrukcija.test.controllers.KompanijaControllerTest;
import com.konstrukcija.test.controllers.OglasControllerTest;
import com.konstrukcija.test.service.KorisnikServiceTest;
import com.konstrukcija.test.service.NekretninaServiceTest;

/**
 * @author Novica Nikolic <nole0223@gmail.com>
 * 
 * Unit test for simple App.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
	KorisnikServiceTest.class,
	NekretninaServiceTest.class,
	AdminControllerTest.class,
	AzuriranjeControllerTest.class,
	KompanijaControllerTest.class,
	NekretninaServiceTest.class,
	OglasControllerTest.class})
public class AppTest {}
