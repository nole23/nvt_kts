package com.konstrukcija;

import java.sql.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Novica Nikolic <nole0223@gmail.com>
 * 
 * Pokretanje aplikacije
 */

@SpringBootApplication
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        SpringApplication.run(App.class, args);
    }
}
