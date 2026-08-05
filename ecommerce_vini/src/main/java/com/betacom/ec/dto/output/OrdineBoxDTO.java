package com.betacom.ec.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OrdineBoxDTO {
	private Integer id;
	private Integer id_ordine;
	private Integer quantita;
	private StatusDTO status;
	private BoxDTO box;
	private CantinaDTO cantina;
}
