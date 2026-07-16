package com.betacom.ec.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IRuoloService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/ruolo") //TODO security in tutti i controller
public class RuoloController {
	private final IRuoloService ruoloService;
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) RuoloRequest req) throws Exception{
		ruoloService.create(req);
		return ResponseEntity.ok(ResponseDTO.builder()
				.msg("created ruolo...")
				.build());
	
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
			ruoloService.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted ruolo...")
					.build());
	}
	
	@GetMapping("getRuoloById")
	public ResponseEntity<Object> getSpedizioneBoxById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(ruoloService.getById(id)) ;
	}
	
	@GetMapping("list")
	public ResponseEntity<Object> listAll() throws Exception {
		return ResponseEntity.ok(ruoloService.listAll());
	}
}
