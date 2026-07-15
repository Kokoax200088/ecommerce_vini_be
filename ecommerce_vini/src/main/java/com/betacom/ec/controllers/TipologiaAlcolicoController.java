package com.betacom.ec.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.output.TipologiaAlcolicoDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.ITipologiaAlcolicoService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api/tipologia-alcolico")
@RequiredArgsConstructor
public class TipologiaAlcolicoController extends ExceptionManager {

	private final ITipologiaAlcolicoService tipologiaS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@Valid @RequestBody TipologiaAlcolicoReq req) throws Exception {
		tipologiaS.create(req);
		return new ResponseEntity<>(
				ResponseDTO.builder().msg(msgS.get("tipologia_create_ok")).build(),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseDTO> remove(@PathVariable("id") Integer id) throws Exception {
		tipologiaS.remove(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("tipologia_remove_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<TipologiaAlcolicoDTO>> list() {
		return ResponseEntity.ok(tipologiaS.listAll());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<TipologiaAlcolicoDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(tipologiaS.getById(id));
	}
}
