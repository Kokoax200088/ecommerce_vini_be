package com.betacom.ec.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VenditoreRequest extends UtenteRequest {
	
	@NotNull (groups = ValidationGroups.Create.class , message ="venditore.indirizzo.missing")
	private String partitaIva;
}
