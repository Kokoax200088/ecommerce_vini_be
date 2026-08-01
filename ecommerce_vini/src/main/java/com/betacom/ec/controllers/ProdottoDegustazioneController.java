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

import com.betacom.ec.dto.input.ProdottoDegustazioneReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ProdottoDegustazioneDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IProdottoDegustazioneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/prodotto-degustazione")
public class ProdottoDegustazioneController {
	
	private final IProdottoDegustazioneService prodS;
	
	@GetMapping("/list")
	public ResponseEntity<Object> list() throws Exception {
		return ResponseEntity.ok(prodS.list());
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@RequestParam (required = true) Integer id) throws Exception {
		return ResponseEntity.ok(prodS.getById(id));
	}
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) ProdottoDegustazioneReq req) throws Exception{
		prodS.create(req);
		return ResponseEntity.ok(ResponseDTO.builder()
				.msg("created...")
				.build());
	
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) ProdottoDegustazioneReq req) throws Exception {
		prodS.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated...")
					.build());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable (required = true) Integer id) throws Exception{
		prodS.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted...")
					.build());
	}
	
	@GetMapping("/getByDegustazione")
    public ResponseEntity<List<ProdottoDegustazioneDTO>> getByDegustazioneId(@RequestParam(required = false) Integer idDegustazione,
			@RequestParam(required = false) Integer idCarrello) {
        return ResponseEntity.ok(prodS.searchByFilter(idDegustazione, idCarrello));
    }
}
