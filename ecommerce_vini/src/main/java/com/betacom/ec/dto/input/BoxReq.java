package com.betacom.ec.dto.input;

import java.util.List;

import com.betacom.ec.models.BoxAlcolico;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.ImmagineBox;
import com.betacom.ec.models.OrdineBox;
import com.betacom.ec.models.ProdottoBox;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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

public class BoxReq {

	@NotNull (groups = {ValidationGroups.Update.class } , message ="Box_update_id_missing")
	private Integer id;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class},message="box_nome_missing")
	@NotBlank(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class},message="box_nome_missing")
	private String nome;
	
	private Double sconto;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class},message="box_id_cantina_missing")
	private Integer cantinaId;
	
	private Cantina cantina;
	private List <BoxAlcolico> listBoxAlcolico;
	private List <ImmagineBox> listImmagine;	
	private List<OrdineBox> listOrdineBox;
	private List <ProdottoBox> listProdottoBox;	
}
