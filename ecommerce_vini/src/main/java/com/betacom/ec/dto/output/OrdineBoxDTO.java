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
	private OrdineDTO ordine;
	private StatusDTO status;
	private BoxDTO box;
	private CantinaDTO cantina;
}
