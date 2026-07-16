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

import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.output.BoxDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IBoxService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api/box")
@RequiredArgsConstructor
public class BoxController extends ExceptionManager {

	private final IBoxService boxS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@Valid @RequestBody BoxReq req) throws Exception {
		boxS.create(req);
		return new ResponseEntity<>(
				ResponseDTO.builder().msg(msgS.get("box_create_ok")).build(),
				HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@Valid @RequestBody BoxReq req) throws Exception {
		boxS.update(req);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("box_update_ok")).build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) throws Exception {
		boxS.delete(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("box_delete_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<BoxDTO>> list(
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) Integer idCantina) throws Exception {
		return ResponseEntity.ok(boxS.list(nome, idCantina));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<BoxDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(boxS.getById(id));
	}
}