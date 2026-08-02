package com.betacom.ec.dto.input;

import java.time.LocalDate;
import java.util.List;

import com.betacom.ec.models.OrdineAlcolico;
import com.betacom.ec.models.OrdineDegustazione;

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
public class OrdineReq {
	
	private Integer id;
	@NotNull(groups=ValidationGroups.Create.class, message="data_missing")
	private LocalDate data_ordine;
	@NotNull(groups=ValidationGroups.Create.class, message="totale_missing")
	private Double totale;
	@NotNull(groups=ValidationGroups.Create.class, message="utente_missing")
	private Integer id_utente;
	@NotNull(groups=ValidationGroups.Create.class, message="status_missing")
	private Integer id_status;
	@NotNull(groups=ValidationGroups.Create.class, message="ordine_alcolico_missing")
	private List<OrdineAlcolico> listOrdineAlcolico;
	@NotNull(groups=ValidationGroups.Create.class, message="ordine_spedizione_missing")
	private List<OrdineDegustazione> listOrdineDegustazione;
	@NotNull(groups=ValidationGroups.Create.class, message="indirizzo_missing")
	private String indirizzoDestinazione;

}
