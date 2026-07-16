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

import com.betacom.ec.dto.input.BoxAlcolicoReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.BoxAlcolicoDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IBoxAlcolicoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/boxalcolico")
public class BoxAlcolicoController {
private final IBoxAlcolicoService baS;
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) BoxAlcolicoReq req) throws Exception{
			baS.create(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("created...")
					.build());
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) BoxAlcolicoReq req) throws Exception {
			baS.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated...")
					.build());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
		baS.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted...")
					.build());
	}
	
	@GetMapping("list")
	public ResponseEntity<List<BoxAlcolicoDTO>> list(@RequestParam(required = false)Integer quantita, 
			@RequestParam(required = false) Integer id_ordine,
			@RequestParam(required = false)Integer id_status,
			@RequestParam(required = false)Integer id_alcolico,
			@RequestParam(required = false)Integer id_cantina) {
		return ResponseEntity.ok(baS.listWithParameters(quantita,id_ordine,id_status,id_alcolico,id_cantina));
	}
	
	@GetMapping("getBoxAlcolicoById")
	public ResponseEntity<Object> getBoxAlcolicoById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(baS.getById(id)) ;
	}
}
