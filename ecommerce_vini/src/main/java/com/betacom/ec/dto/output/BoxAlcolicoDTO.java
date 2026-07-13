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
    private BoxDTO box;
    private AlcolicoDTO alcolico;
    private Integer quantita;
}