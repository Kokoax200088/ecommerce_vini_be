package com.betacom.ec.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OrdineAlcolicoDTO {
	private Integer id;
	private Integer id_ordine;
	private StatusDTO status;
	private AlcolicoDTO alcolico;
	private CantinaDTO cantina;
}
