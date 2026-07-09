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
public class AlcolicoDTO {

	private Integer id_alcolico;

	private String nome;

	private Integer annata;

	private Integer id_tipologia_alcolico;

	private Integer id_colore;

	private Integer gradazione;

	private String descrizione;

	private String provenienza;

	private String immagine;
}
