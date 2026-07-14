package com.betacom.ec.dto.output;

import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CantinaDTO {
    private Integer id;
    private String nome;
    private Integer id_venditore;
    private Integer id_posizione;
    private List<CantinaAlcolicoDTO> listCantinaAlcolico;
    private List<RatingCantinaDTO> listRatingCantina;
    private List<BoxDTO>listBox;
    private List<DegustazioneDTO> listDegustazione;
    private List<ImmagineCantinaDTO> listImmagineCantina;
}