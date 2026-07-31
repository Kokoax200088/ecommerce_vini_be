package com.betacom.ec.dto.output;

import java.util.List;

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

	private Integer id;

	private Integer id_venditore;

	private String nome;

	private Integer annata;

	private TipologiaAlcolicoDTO tipologiaAlcolico;

	private ColoreDTO colore;

	private Integer gradazione;

	private String descrizione;

	private String provenienza;

	private Double prezzo;

	private List<CaratteristicaDTO> caratteristiche;

    private List<RatingAlcolicoDTO> listRatingAlcolico;
}
