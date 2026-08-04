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
import com.betacom.ec.repository.IClienteRepository;
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
	private final IClienteRepository cR;

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
		boolean isCliente = authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));

		Integer idVenditore = null;

		if (isVenditore) {
			String email = authentication.getName();
			idVenditore = vR.findIdByUtenteEmail(email).orElseThrow(() -> new EcommerceVinoException("utente.ntfnd"));
		}

		if (isCliente) {
			String email = authentication.getName();
			log.info("DEBUG isCliente=true email={}", email);
			int id_utente = cR.findIdByUtenteEmail(email).orElseThrow(() -> new EcommerceVinoException("utenteId.ntfnd"));
			id_cliente = cR.findIdByUtenteId(id_utente).orElseThrow(() -> new EcommerceVinoException("cliente.ntfnd"));
			log.info("DEBUG isCliente=true email={} id_cliente_risolto={}", email, id_cliente);
		}

		return ResponseEntity.ok(
				saS.listWithParameters(corriere, codice_tracciamento, id_cantina,
						id_ordine_alcolico, id_cliente, id_status, idVenditore));
	}

	@GetMapping("getSpedizioneAlcolicoById")
	public ResponseEntity<Object> getSpedizioneAlcolicoById(@RequestParam(required = true) Integer id,
			Authentication authentication) throws Exception {

		Object result = saS.getById(id);

		boolean isCliente = authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));

		if (isCliente && result instanceof SpedizioneAlcolicoDTO spedizione) {
			String email = authentication.getName();
			Integer idClienteAutenticato = cR.findIdByUtenteEmail(email)
					.orElseThrow(() -> new EcommerceVinoException("spedalc.ntfnd"));

			Integer idClienteSpedizione = spedizione.getCliente() != null ? spedizione.getCliente().getId() : null;

			if (!idClienteAutenticato.equals(idClienteSpedizione)) {
				throw new EcommerceVinoException("spedalc.forbidden");
			}
		}

		return ResponseEntity.ok(result);
	}
}