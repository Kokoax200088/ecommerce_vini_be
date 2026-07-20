package com.betacom.ec.controllers;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.LoginDTO;
import com.betacom.ec.services.interfaces.IUtenteService;
import com.betacom.ec.services.interfaces.JwtService;

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

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Validated(ValidationGroups.Login.class) UtenteRequest request)
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
	            .path("/rest/auth")
	            .maxAge(Duration.ofDays(7))
	            .build();

		r = LoginDTO.builder()
				.accessToken(token)
				.tokenType("Bearer")
				.build();

	    return ResponseEntity.ok()
	            .header(HttpHeaders.SET_COOKIE,refreshCookie.toString())
	            .body(r);

	}

	@GetMapping("/me")
	public ResponseEntity<Object> me(Authentication authentication) throws Exception {
		UtenteRequest req = new UtenteRequest();
		req.setEmail(authentication.getName());
		return ResponseEntity.ok(utS.me(req));
	}
	
//	  TODO in attesa di apprendere la parte jwt
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;

//    // Costruttore per la Dependency Injection
//    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtService = jwtService;
//    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        // 1. Autentica l'utente usando il manager di Spring Security
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        // 2. Se l'autenticazione va a buon fine, genera il token
//        String token = jwtService.generateToken(authentication.getName());
//
//        // 3. Rispondi al Frontend inviando il token in un oggetto JSON
//        Map<String, String> response = new HashMap<>();
//        response.put("token", token);
//
//        return ResponseEntity.ok(response);
//    }
}
