package com.betacom.ec.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OrdineDegustazioneDTO {
	private Integer id;
	private Integer id_ordine;
	private Integer id_status;
	private Integer id_degustazione;
	private Integer id_cantina;
}
