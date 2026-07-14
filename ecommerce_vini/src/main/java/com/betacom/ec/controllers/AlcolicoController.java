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
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.output.AlcolicoDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IAlcolicoService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alcolico")
@RequiredArgsConstructor
public class AlcolicoController extends ExceptionManager {

	private final IAlcolicoService alcolicoS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@Valid @RequestBody AlcolicoReq req) throws Exception {
		alcolicoS.create(req);
		return new ResponseEntity<>(
				ResponseDTO.builder().msg(msgS.get("alcolico_create_ok")).build(),
				HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@Valid @RequestBody AlcolicoReq req) throws Exception {
		alcolicoS.update(req);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("alcolico_update_ok")).build());
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseDTO> remove(@PathVariable("id") Integer id) throws Exception {
		alcolicoS.remove(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("alcolico_remove_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<AlcolicoDTO>> list() {
		return ResponseEntity.ok(alcolicoS.listAll());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<AlcolicoDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(alcolicoS.getById(id));
	}
}
