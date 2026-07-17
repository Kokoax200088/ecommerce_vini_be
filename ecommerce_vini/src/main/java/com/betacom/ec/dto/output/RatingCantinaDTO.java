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
	private Integer id;
	private Integer id_cantina;
	private Integer id_cliente;
	private Double valutazione;
	private String commento;
}
