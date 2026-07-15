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
    private Integer idCantina; //id solo per evitare casini
    private AlcolicoDTO alcolico; //in teoria qui può andare l'info completa
    private Integer quantita;
}