package com.betacom.ec.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.PosizioneDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IPosizioneService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api/posizione")
@RequiredArgsConstructor
public class PosizioneController extends ExceptionManager {

	private final IPosizioneService posizioneS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody (required = true) @Validated(ValidationGroups.Create.class) PosizioneReq req) throws Exception {
		posizioneS.create(req);
		return new ResponseEntity<>(
				ResponseDTO.builder().msg(msgS.get("posizione_create_ok")).build(),
				HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@RequestBody (required = true) @Validated(ValidationGroups.Update.class) PosizioneReq req) throws Exception {
		posizioneS.update(req);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("posizione_update_ok")).build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) throws Exception {
		posizioneS.delete(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("posizione_delete_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<PosizioneDTO>> list(
			@RequestParam(required = false) String descrizione) throws Exception {
		return ResponseEntity.ok(posizioneS.listBySearchString(descrizione));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<PosizioneDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(posizioneS.getById(id));
	}
}