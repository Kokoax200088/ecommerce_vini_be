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

import com.betacom.ec.dto.input.PrenotazioneDegustazioneReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.PrenotazioneDegustazioneDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IPrenotazioneDegustazioneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/prenotazionedegustazione")
public class PrenotazioneDegustazioneController {
private final IPrenotazioneDegustazioneService pdS;
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) PrenotazioneDegustazioneReq req) throws Exception{
			pdS.create(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("created...")
					.build());
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) PrenotazioneDegustazioneReq req) throws Exception {
			pdS.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated...")
					.build());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
		pdS.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted...")
					.build());
	}
	@GetMapping("list")
	public ResponseEntity<List<PrenotazioneDegustazioneDTO>> list(Integer id, 
			Integer id_degustazione,
			Integer id_status,
			Integer id_cantina) {
		return ResponseEntity.ok(pdS.listWithParameters(id,id_degustazione,id_status,id_cantina));
	}
	@GetMapping("getPrenotazioneDegustazioneById")
	public ResponseEntity<Object> getPrenotazioneDegustazioneById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(pdS.getById(id)) ;
	}

}
