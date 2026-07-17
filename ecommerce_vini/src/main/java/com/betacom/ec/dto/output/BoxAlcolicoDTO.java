package com.betacom.ec.dto.output;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoxAlcolicoDTO {
    private Integer id;
    private Integer id_box;
    private AlcolicoDTO alcolico;
    private Integer quantita;
}