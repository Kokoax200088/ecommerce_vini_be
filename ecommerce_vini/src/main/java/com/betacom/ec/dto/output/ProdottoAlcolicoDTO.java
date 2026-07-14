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
public class ProdottoAlcolicoDTO {
	private Integer id;
	private CarrelloDTO carrello;
	private AlcolicoDTO alcolico;
	private CantinaDTO cantina;
	private Integer quantità;
}
