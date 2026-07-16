package com.betacom.ec.controllers;

import java.util.List;

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

import com.betacom.ec.dto.input.SpedizioneBoxReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.SpedizioneBoxDTO;
import com.betacom.ec.services.interfaces.ISpedizioneBoxService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/spedizionebox")
public class SpedizioneBoxController {
	
	private final ISpedizioneBoxService sbS;
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) SpedizioneBoxReq req) throws Exception{
			sbS.create(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("created...")
					.build());
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) SpedizioneBoxReq req) throws Exception {
			sbS.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated...")
					.build());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
			sbS.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted...")
					.build());
	}
	
	@GetMapping("list")
	public ResponseEntity<List<SpedizioneBoxDTO>> list(@RequestParam(required = false)String corriere,
			@RequestParam(required = false)String codice_tracciamento,
			@RequestParam(required = false)Integer id_cantina, 
			@RequestParam(required = false)Integer id_ordine_alcolico,
			@RequestParam(required = false)Integer id_cliente,
			@RequestParam(required = false)Integer id_status) {
		return ResponseEntity.ok(sbS.listWithParameters(corriere,codice_tracciamento,id_cantina,id_ordine_alcolico,id_cliente,id_status));
	}
	@GetMapping("getSpedizioneBoxById")
	public ResponseEntity<Object> getSpedizioneBoxById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(sbS.getById(id)) ;
	}
}
