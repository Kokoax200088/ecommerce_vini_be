package com.betacom.ec.dto.output;

import java.util.List;
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
    private CantinaDTO cantina;
    private List<BoxAlcolicoDTO> listBoxAlcolico;
    private List<ImmagineBoxDTO> listImmagineBox;
}