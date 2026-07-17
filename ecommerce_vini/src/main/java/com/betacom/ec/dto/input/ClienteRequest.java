package com.betacom.ec.dto.input;

import java.util.List;

import com.betacom.ec.models.RatingAlcolico;
import com.betacom.ec.models.RatingCantina;

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
	
//	private Integer idCarrello;
//	private List<RatingAlcolico> listRatingAlcolico;
//	private List<RatingCantina> listRatingCantina;
}
