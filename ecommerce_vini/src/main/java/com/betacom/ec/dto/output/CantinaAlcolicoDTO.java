package com.betacom.ec.dto.output;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CantinaAlcolicoDTO {
    private Integer id;
    private CantinaDTO cantina;
    private AlcolicoDTO alcolico;
    private Integer quantita;
}