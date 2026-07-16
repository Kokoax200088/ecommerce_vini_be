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

import com.betacom.ec.dto.input.RatingCantinaReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IRatingCantinaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/rating-cantina")
public class RatingCantinaController {
	private final IRatingCantinaService ratS;

	@GetMapping("/list")
	public ResponseEntity<Object> list(
			@RequestParam (required = false)  String nomeCantina,
			@RequestParam (required = false)  Integer id_utente,
			@RequestParam (required = false)  Integer valutazione
			) throws Exception {
		return ResponseEntity.ok(ratS.list(nomeCantina, id_utente, valutazione));
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@RequestParam (required = true) Integer id) throws Exception {
		return ResponseEntity.ok(ratS.getById(id));
	}
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) RatingCantinaReq req) throws Exception{
		ratS.create(req);
		return ResponseEntity.ok(ResponseDTO.builder()
				.msg("created...")
				.build());
	
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable (required = true) Integer id) throws Exception{
			ratS.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted...")
					.build());
	}
}
