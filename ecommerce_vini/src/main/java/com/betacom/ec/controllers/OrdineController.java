package com.betacom.ec.controllers;

import java.time.LocalDate;
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

import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IOrdineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/ordine")
public class OrdineController {
	
private final IOrdineService oS;
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) OrdineReq req) throws Exception{
			OrdineDTO creato = oS.create(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("created...")
					.id(creato.getId())
					.build());
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) OrdineReq req) throws Exception {
			oS.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated...")
					.build());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
			oS.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted...")
					.build());
	}
	
	@GetMapping("list")
	public ResponseEntity<List<OrdineDTO>> list(@RequestParam(required = false)LocalDate data, 
			@RequestParam(required = false)Double totale, 
			@RequestParam(required = false)Integer id_status, 
			@RequestParam(required = false)Integer id_utente, 
			@RequestParam(required = false)String indirizzo_destinazione) {
		return ResponseEntity.ok(oS.listWithParameters(data,totale,id_status,id_utente,indirizzo_destinazione));
	}
	@GetMapping("getOrdineById")
	public ResponseEntity<Object> getOrdineById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(oS.getById(id)) ;
	}
}
