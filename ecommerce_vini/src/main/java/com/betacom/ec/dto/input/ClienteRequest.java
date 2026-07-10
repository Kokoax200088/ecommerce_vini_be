package com.betacom.ec.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteRequest extends UtenteRequest {
	
	@NotNull (groups = ValidationGroups.Create.class , message ="cliente.indirizzo.missing")
	private String indirizzo;
}
