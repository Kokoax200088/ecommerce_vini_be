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

import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.CantinaDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.ICantinaService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api/cantina")
@RequiredArgsConstructor
public class CantinaController extends ExceptionManager {

	private final ICantinaService cantinaS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<CantinaDTO> create(@RequestBody (required = true) @Validated(ValidationGroups.Create.class) CantinaReq req) throws Exception {
		CantinaDTO nuovaCantina = cantinaS.create(req);
		return new ResponseEntity<>(nuovaCantina, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@RequestBody (required = true) @Validated(ValidationGroups.Update.class) CantinaReq req) throws Exception {
		cantinaS.update(req);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("cantina_update_ok")).build());
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseDTO> remove(@PathVariable("id") Integer id) throws Exception {
		cantinaS.delete(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("cantina_remove_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<CantinaDTO>> list(
			@RequestParam(required = false) String nomeCantina,
			@RequestParam(required = false) Integer idVenditore) throws Exception {
		return ResponseEntity.ok(cantinaS.listBySearchString(nomeCantina, idVenditore));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<CantinaDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(cantinaS.getById(id));
	}
}