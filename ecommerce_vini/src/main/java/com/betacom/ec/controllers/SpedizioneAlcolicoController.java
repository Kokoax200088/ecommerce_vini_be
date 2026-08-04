package com.betacom.ec.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import com.betacom.ec.dto.input.SpedizioneAlcolicoReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.repository.IVenditoreRepository;
import com.betacom.ec.services.interfaces.ISpedizioneAlcolicoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/spedizionealcolico")
public class SpedizioneAlcolicoController {
	private final ISpedizioneAlcolicoService saS;
	private final IVenditoreRepository vR;

	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody(required = true) @Validated(ValidationGroups.Create.class) SpedizioneAlcolicoReq req)
			throws Exception {
		saS.create(req);
		return ResponseEntity.ok(ResponseDTO.builder().msg("created...").build());
	}

	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody(required = true) @Validated(ValidationGroups.Update.class) SpedizioneAlcolicoReq req)
			throws Exception {
		saS.update(req);
		return ResponseEntity.ok(ResponseDTO.builder().msg("updated...").build());
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable(required = true) Integer id) throws Exception {
		saS.delete(id);
		return ResponseEntity.ok(ResponseDTO.builder().msg("deleted...").build());
	}

	@GetMapping("list")
	public ResponseEntity<List<SpedizioneAlcolicoDTO>> list(
			@RequestParam(required = false) String corriere,
			@RequestParam(required = false) String codice_tracciamento,
			@RequestParam(required = false) Integer id_cantina,
			@RequestParam(required = false) Integer id_ordine_alcolico,
			@RequestParam(required = false) Integer id_cliente,
			@RequestParam(required = false) Integer id_status,
			Authentication authentication) {

		boolean isVenditore = authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_VENDITORE"));

		Integer idVenditore = null;

		if (isVenditore) {
			String email = authentication.getName();
			idVenditore = vR.findIdByUtenteEmail(email).orElseThrow(() -> new EcommerceVinoException("spedalc.ntfnd"));
		}

		return ResponseEntity.ok(
				saS.listWithParameters(corriere, codice_tracciamento, id_cantina,
						id_ordine_alcolico, id_cliente, id_status, idVenditore));
	}

	@GetMapping("getSpedizioneAlcolicoById")
	public ResponseEntity<Object> getSpedizioneAlcolicoById(@RequestParam(required = true) Integer id)
			throws Exception {
		return ResponseEntity.ok(saS.getById(id));
	}

}
