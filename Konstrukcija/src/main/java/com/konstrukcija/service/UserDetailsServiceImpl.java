package com.konstrukcija.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Korisnik;
import com.konstrukcija.model.UserAuthority;
import com.konstrukcija.repository.KorisnikRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	  private KorisnikRepository userRepository;

	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Korisnik user = userRepository.findByUsername(username);

	    if (user == null) {
	      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
	    } else {
    	  	
	    	List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
	    	UserAuthority ua = user.getUserAuthorities();
	    	grantedAuthorities.add(new SimpleGrantedAuthority(ua.getAdmin().getName()));
	    	
	    	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),grantedAuthorities);
	    }
	  }

}


