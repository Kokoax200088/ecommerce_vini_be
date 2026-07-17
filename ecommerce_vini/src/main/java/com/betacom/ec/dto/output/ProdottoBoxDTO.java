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
public class ProdottoBoxDTO {
	private Integer id;
	private Integer id_carrello;
	private BoxDTO box;
	private CantinaDTO cantina;
	private Integer quantità;
}
