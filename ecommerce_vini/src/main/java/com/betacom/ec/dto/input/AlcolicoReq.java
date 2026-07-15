package com.betacom.ec.dto.input;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AlcolicoReq {

	private Integer id_alcolico;

	@NotNull
	private Integer id_venditore;

	@NotBlank
	private String nome;

	private Integer annata;

	@NotNull
	private Integer id_tipologia_alcolico;

	@NotNull
	private Integer id_colore;

	private Integer gradazione;

	private String descrizione;

	private String provenienza;

	private String immagine;

	@NotNull
	private Double prezzo;

	private List<Integer> id_caratteristiche;
}
