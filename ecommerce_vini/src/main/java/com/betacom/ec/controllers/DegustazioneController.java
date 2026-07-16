package com.betacom.ec.controllers;

import java.time.LocalDateTime;
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

import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.DegustazioneDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IDegustazioneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/degustazione")
public class DegustazioneController {
private final IDegustazioneService dS;
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) DegustazioneReq req) throws Exception{
			dS.create(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("created...")
					.build());
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) DegustazioneReq req) throws Exception {
			dS.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated...")
					.build());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
			dS.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted...")
					.build());
	}
	
	@GetMapping("list")
	public ResponseEntity<List<DegustazioneDTO>> list(@RequestParam(required = false)String nome,
			@RequestParam(required = false)String descrizione,
			@RequestParam(required = false)Double prezzo,
			@RequestParam(required = false)LocalDateTime dataInizio,
			@RequestParam(required = false)LocalDateTime dataFine,
			@RequestParam(required = false)Integer id_cantina,
			@RequestParam(required = false)Integer id_carrello) {
		return ResponseEntity.ok(dS.listWithParameters(nome,descrizione,prezzo,dataInizio,dataFine,id_cantina,id_carrello));
	}
	
	@GetMapping("getDegustazioneById")
	public ResponseEntity<Object> getDegustazioneById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(dS.getById(id)) ;
	}
}
