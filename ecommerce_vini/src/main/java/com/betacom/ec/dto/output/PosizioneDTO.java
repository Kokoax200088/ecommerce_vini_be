package com.betacom.ec.dto.output;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PosizioneDTO {
    private Integer id;
    private Double latitudine;
    private Double longitudine;
    private String descrizione;
    private CantinaDTO cantina;
}