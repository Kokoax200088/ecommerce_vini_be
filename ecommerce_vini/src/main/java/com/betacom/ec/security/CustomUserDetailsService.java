package com.betacom.ec.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.betacom.ec.models.Utente;
import com.betacom.ec.repository.IUtenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final IUtenteRepository utenteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.debug("loadUserByUsername: {}", email);
		
		Utente ut = utenteRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("login_invalid"));

		return User.builder()
                .username(ut.getEmail())
                .password(ut.getPassword()) 
                .roles(ut.getRuolo().getNome().toUpperCase())       // "admin", "user" o "seller"
                .build();

	}
}