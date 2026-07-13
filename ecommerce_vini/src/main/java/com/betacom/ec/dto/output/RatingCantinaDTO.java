package com.betacom.ec.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingCantinaDTO {
	//private CantinaDTO cantina;
	//private ClienteDTO cliente;
	private Double valutazione;
	private String commento;
}
