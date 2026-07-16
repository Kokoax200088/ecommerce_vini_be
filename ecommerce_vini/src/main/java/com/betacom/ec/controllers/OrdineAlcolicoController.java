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

import com.betacom.ec.dto.input.OrdineAlcolicoReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IOrdineAlcolicoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/ordinealcolico")
public class OrdineAlcolicoController {
	private final IOrdineAlcolicoService oaS;

	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) OrdineAlcolicoReq req) throws Exception{
			oaS.create(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("created...")
					.build());
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) OrdineAlcolicoReq req) throws Exception {
			oaS.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated...")
					.build());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
			oaS.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted...")
					.build());
	}
	
	@GetMapping("list")
	public ResponseEntity<List<OrdineAlcolicoDTO>> list(@RequestParam(required = false)Integer quantita,
			@RequestParam(required = false)Integer id_ordine,
			@RequestParam(required = false)Integer id_status,
			@RequestParam(required = false)Integer id_alcolico,
			@RequestParam(required = false)Integer id_cantina) {
		return ResponseEntity.ok(oaS.listWithParameters(quantita,id_ordine,id_status,id_alcolico,id_cantina));
	}
	@GetMapping("getOrdineAlcolicoById")
	public ResponseEntity<Object> getOrdineAlcolicoById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(oaS.getById(id)) ;
	}
}
