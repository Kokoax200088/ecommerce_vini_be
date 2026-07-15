package com.betacom.ec.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SpedizioneBoxDTO {

	private Integer id;
	private String corriere;
	private String codice_tracciamento;
	private Integer id_cantina;
	private Integer id_cliente;
	private Integer id_status;
	private Integer id_box;
}
