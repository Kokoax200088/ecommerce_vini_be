package com.betacom.ec.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.OrdineDegustazioneRequest;
import com.betacom.ec.dto.output.OrdineDegustazioneDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IOrdineDegustazioneService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api/ordine-degustazione")
@RequiredArgsConstructor
public class OrdineDegustazioneController extends ExceptionManager {

	private final IOrdineDegustazioneService ordineDegustazioneS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@Valid @RequestBody OrdineDegustazioneRequest req) throws Exception {
		ordineDegustazioneS.create(req);
		return new ResponseEntity<>(
				ResponseDTO.builder().msg(msgS.get("ordineDegustazione_create_ok")).build(),
				HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@Valid @RequestBody OrdineDegustazioneRequest req) throws Exception {
		ordineDegustazioneS.update(req);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("ordineDegustazione_update_ok")).build());
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseDTO> remove(@PathVariable("id") Integer id) throws Exception {
		ordineDegustazioneS.remove(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("ordineDegustazione_remove_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<OrdineDegustazioneDTO>> list(
			@RequestParam(required = false) Integer quantita,
			@RequestParam(required = false) Integer idOrdine,
			@RequestParam(required = false) Integer idStatus,
			@RequestParam(required = false) Integer idDegustazione,
			@RequestParam(required = false) Integer idCantina) {
		return ResponseEntity.ok(ordineDegustazioneS.listWithParameters(quantita, idOrdine, idStatus, idDegustazione, idCantina));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<OrdineDegustazioneDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(ordineDegustazioneS.getById(id));
	}
}
