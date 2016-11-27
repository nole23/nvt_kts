package com.konstrukcija.testiranje.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Treba prepisati sve kao u bazi
//

@Entity
public class Nekretnina {

	@Id
	@GeneratedValue
	private Integer id;
	
	String Naziv_nekretnine;
	
	Double Cena;
	
	String Povrsina;
	
	String Vrsta_grejanja;
	
	
}
