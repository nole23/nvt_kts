package com.konstrukcija.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.konstrukcija.model.Korisnik;
import com.konstrukcija.repository.KorisnikRepository;

@Service
public class KorisnikDetailsServiceImpl implements UserDetailsService {

	@Autowired
	  private KorisnikRepository userRepository;

	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Korisnik user = userRepository.findByUsername(username);

	    if (user == null) {
	      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
	    } else {
  	  	
	    	List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().
	    			stream()
	                .map(authority -> new SimpleGrantedAuthority(authority.getAdmin().getName()))
	                .collect(Collectors.toList());
	    	
	    	return new org.springframework.security.core.userdetails.User(
	    		  user.getUsername(),
	    		  user.getPassword(),
	    		  grantedAuthorities);
	    }
	  }
}
