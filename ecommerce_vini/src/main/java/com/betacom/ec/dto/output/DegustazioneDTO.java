package com.betacom.ec.dto.output;

import java.time.LocalDateTime;
import java.util.List;

import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.ImmagineDegustazione;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DegustazioneDTO {
    private Integer id;
    private String nome;
    private String descrizione;
    private Double prezzo;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private CantinaDTO cantina;
    private List<Alcolico> alcolici;
    private List<ImmagineDegustazione> immagini;
}