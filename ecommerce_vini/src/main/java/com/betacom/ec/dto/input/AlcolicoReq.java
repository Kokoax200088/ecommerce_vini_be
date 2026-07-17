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

	@NotNull(groups = {ValidationGroups.Update.class}, message ="alcolico_update_id_missing")
	private Integer id_alcolico;

	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="alcolico_id_venditore_missing")
	private Integer id_venditore;

	@NotBlank(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="alcolico_nome_missing")
	private String nome;

	private Integer annata;

	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="alcolico_id_tipologia_alcolico_missing")
	private Integer id_tipologia_alcolico;

	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="alcolico_id_colore_missing")
	private Integer id_colore;

	private Integer gradazione;

	private String descrizione;

	private String provenienza;

	private String immagine;

	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="alcolico_prezzo_missing")
	private Double prezzo;

	private List<Integer> id_caratteristiche;
}
