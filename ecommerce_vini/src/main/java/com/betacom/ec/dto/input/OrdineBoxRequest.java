package com.betacom.ec.dto.input;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrdineBoxRequest {
	private Integer id;
	private LocalDate data_ordine;
	private Integer ordineId;
	private Integer boxId;
	private Integer statusId;
	private Integer cantinaId;
	private Integer quantita;
}
