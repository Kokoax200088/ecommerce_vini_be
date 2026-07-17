package com.betacom.ec.dto.output;

import java.util.List;

import com.betacom.ec.models.BoxAlcolico;
import com.betacom.ec.models.ImmagineBox;
import com.betacom.ec.models.OrdineBox;
import com.betacom.ec.models.ProdottoBox;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoxDTO {
    private Integer id;
    private String nome;
    private Double sconto;
    private Integer id_cantina;
    private List <BoxAlcolicoDTO> listBoxAlcolico;
	private List <ImmagineBoxDTO> listImmagine;
}