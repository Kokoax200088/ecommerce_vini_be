package com.betacom.ec.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/auth")
public class AuthController {

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
