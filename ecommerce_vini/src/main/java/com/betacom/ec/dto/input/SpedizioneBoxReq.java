package com.betacom.ec.dto.input;

import jakarta.validation.constraints.NotNull;
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
public class SpedizioneBoxReq {
	private Integer id;
	@NotNull(groups=ValidationGroups.Create.class, message="corriere_missing")
	private String corriere;
	@NotNull(groups=ValidationGroups.Create.class, message="codice_tracciamento_missing")
	private String codice_tracciamento;
	@NotNull(groups=ValidationGroups.Create.class, message="cantina_missing")
	private Integer id_cantina;
	@NotNull(groups=ValidationGroups.Create.class, message="cliente_missing")
	private Integer id_cliente;
	@NotNull(groups=ValidationGroups.Create.class, message="status_missing")
	private Integer id_status;
	@NotNull(groups=ValidationGroups.Create.class, message="box_missing")
	private Integer id_box;
}
