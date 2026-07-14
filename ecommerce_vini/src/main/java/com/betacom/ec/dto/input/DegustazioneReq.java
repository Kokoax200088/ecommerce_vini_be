package com.betacom.ec.dto.input;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DegustazioneReq {

	@NotNull(groups = {ValidationGroups.Update.class}, message ="degustazione_update_id_missing")
	private Integer id;
	
	@NotBlank(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="degustazione_nome_empty")
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="degustazione_nome_missing")
	private String nome;
	
	private String descrizione;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="degustazione_prezzo_missing")
	private Double prezzo;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="degustazione_data_inizio_missing")
	private LocalDateTime dataInizio;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="degustazione_data_fine_missing")
	private LocalDateTime dataFine;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="degustazione_id_cantina_missing")
	private Integer cantinaId;
	
}