package com.betacom.ec.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.ChangePasswordRequest;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IUtenteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/utente")
public class UtenteController {
	private final IUtenteService utenteService;
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) UtenteRequest req) throws Exception{
		utenteService.create(req);
		return ResponseEntity.ok(ResponseDTO.builder()
				.msg("created utente...")
				.build());
	
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) UtenteRequest req) throws Exception {
			utenteService.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated utente...")
					.build());
	}
	
	@PatchMapping("changePassword")
	public ResponseEntity<ResponseDTO> changePassword(@RequestBody (required = true) ChangePasswordRequest req) throws Exception{ //tutti i campi sono considerati not null nella req
		utenteService.changePassword(req);
		return ResponseEntity.ok(ResponseDTO.builder()
				.msg("changed password utente...")
				.build());
				
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
			utenteService.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted utente...")
					.build());
	}
	
	@GetMapping("getUtenteById")
	public ResponseEntity<Object> getById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(utenteService.getById(id)) ;
	}
	
	@GetMapping("listWithParameters")
	public ResponseEntity<Object> list(
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String cognome,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String dataNascita,
			@RequestParam(required = false) String ruolo) throws Exception {
		return ResponseEntity.ok(utenteService.listBySearchString(nome, cognome, email, dataNascita, ruolo));
	}
}
