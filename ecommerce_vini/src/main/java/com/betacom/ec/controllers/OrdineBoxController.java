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

import com.betacom.ec.dto.input.OrdineBoxRequest;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.OrdineBoxDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IOrdineBoxService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api/ordine-box")
@RequiredArgsConstructor
public class OrdineBoxController extends ExceptionManager {

	private final IOrdineBoxService ordineBoxS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody (required = true) @Validated(ValidationGroups.Create.class) OrdineBoxRequest req) throws Exception {
		ordineBoxS.create(req);
		return new ResponseEntity<>(
				ResponseDTO.builder().msg(msgS.get("ordineBox_create_ok")).build(),
				HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@RequestBody (required = true) @Validated(ValidationGroups.Update.class) OrdineBoxRequest req) throws Exception {
		ordineBoxS.update(req);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("ordineBox_update_ok")).build());
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseDTO> remove(@PathVariable("id") Integer id) throws Exception {
		ordineBoxS.remove(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("ordineBox_remove_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<OrdineBoxDTO>> list(
			@RequestParam(required = false) Integer quantita,
			@RequestParam(required = false) Integer idOrdine,
			@RequestParam(required = false) Integer idStatus,
			@RequestParam(required = false) Integer idBox,
			@RequestParam(required = false) Integer idCantina) {
		return ResponseEntity.ok(ordineBoxS.listWithParameters(quantita, idOrdine, idStatus, idBox, idCantina));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<OrdineBoxDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(ordineBoxS.getById(id));
	}
}
