package com.betacom.ec.controllers;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.LoginRequest;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.LoginDTO;
import com.betacom.ec.security.CustomUserDetailsService;
import com.betacom.ec.security.interfaces.JwtService;
import com.betacom.ec.services.interfaces.IUtenteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/auth")
public class AuthController {


	private final IUtenteService utS;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final CustomUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Validated(ValidationGroups.Login.class) LoginRequest request)
			throws Exception {
		Object r = new Object();
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		String token = jwtService.generateAccessToken(authentication);
		String refreshToken = jwtService.generateRefreshToken(authentication);
		
		// generate cookie
	    ResponseCookie refreshCookie = ResponseCookie
	            .from("refreshToken", refreshToken)
	            .httpOnly(true)
	            .secure(false) // true in produzione con HTTPS
	            .sameSite("Lax")
	            .path("/rest/api/auth")
	            .maxAge(Duration.ofDays(7)) // dovrei recuperarlo dalla variabile che avevo prestabilito
	            .build();

		r = LoginDTO.builder()
				.accessToken(token)
				.tokenType("Bearer")
				.build();

	    return ResponseEntity.ok()
	            .header(HttpHeaders.SET_COOKIE,refreshCookie.toString())
	            .body(r);

	}

	@PostMapping("/refresh")
	public ResponseEntity<LoginDTO> refresh(@CookieValue(name = "refreshToken", required = false)
	        String refreshToken) throws Exception{

	    if (refreshToken == null || refreshToken.isBlank()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    // refreshToken is valid?
	    if (!jwtService.isValidRefreshToken(refreshToken)) {  
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .build();
	    }

	    String username =jwtService.extractUsername(refreshToken);

	    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	    Authentication authentication = new UsernamePasswordAuthenticationToken(
	                    userDetails,
	                    null,
	                    userDetails.getAuthorities()
	            );

	    String newAccessToken = jwtService.generateAccessToken(authentication);

	    LoginDTO response = LoginDTO.builder()
	            .accessToken(newAccessToken)
	            .tokenType("Bearer")
	            .build();

	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Void> logout() {

	    ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
	            .httpOnly(true)
	            .secure(false)        // true in produzione con HTTPS
	            .sameSite("Lax")
	            .path("/rest/api/auth")
	            .maxAge(0)            // maxAge = 0 -> remove cookie 
	            .build();

	    return ResponseEntity.ok()
	            .header(HttpHeaders.SET_COOKIE, cookie.toString())
	            .build();
	}
	
	@GetMapping("/me")
	public ResponseEntity<Object> me(Authentication authentication) throws Exception {
		UtenteRequest req = new UtenteRequest();
		req.setEmail(authentication.getName());
		return ResponseEntity.ok(utS.me(req));
	}
}
