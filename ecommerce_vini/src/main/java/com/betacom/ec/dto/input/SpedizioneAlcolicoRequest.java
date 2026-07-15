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
public class SpedizioneAlcolicoRequest {
	private Integer id;
	@NotNull(groups=ValidationGroups.Create.class, message="corriere_missing")
	private String corriere;
	@NotNull(groups=ValidationGroups.Create.class, message="codice_traccia_missing")
	private String codice_tracciamento;
	@NotNull(groups=ValidationGroups.Create.class, message="cantina_missing")
	private Integer id_cantina;
	@NotNull(groups=ValidationGroups.Create.class, message="cliente_missing")
	private Integer id_cliente;
	@NotNull(groups=ValidationGroups.Create.class, message="status_missing")
	private Integer id_status;
}
