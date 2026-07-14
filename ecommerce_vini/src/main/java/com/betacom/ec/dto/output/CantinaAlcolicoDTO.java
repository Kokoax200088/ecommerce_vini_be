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
    private Integer id_cantina;
    private Integer id_alcolico;
    private Integer quantita;
}