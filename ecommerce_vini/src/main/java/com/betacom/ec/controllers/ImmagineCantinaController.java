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

import com.betacom.ec.dto.input.ImmagineCantinaReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ImmagineCantinaDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IImmagineCantinaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/immagine-cantina")
public class ImmagineCantinaController {

	private final IImmagineCantinaService immS;

	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody(required = true) @Validated(ValidationGroups.Create.class) ImmagineCantinaReq req)
			throws Exception {
		immS.create(req);
		return ResponseEntity.ok(ResponseDTO.builder().msg("created...").build());
	}

	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody(required = true) @Validated(ValidationGroups.Update.class) ImmagineCantinaReq req)
			throws Exception {
		immS.update(req);
		return ResponseEntity.ok(ResponseDTO.builder().msg("updated...").build());
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable(required = true) Integer id) throws Exception {
		immS.delete(id);
		return ResponseEntity.ok(ResponseDTO.builder().msg("deleted...").build());
	}

	@GetMapping("list")
	public ResponseEntity<List<ImmagineCantinaDTO>> list(@PathVariable(required = true) Integer idCantina) throws Exception {
		return ResponseEntity.ok(immS.listBySearchString(idCantina));
	}

	@GetMapping("getById")
	public ResponseEntity<Object> getImmagineDegustazioneById(@RequestParam(required = true) Integer id)
			throws Exception {
		return ResponseEntity.ok(immS.getById(id));
	}

}
