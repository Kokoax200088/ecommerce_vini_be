package com.betacom.ec.dto.output;

import java.time.LocalDateTime;
import java.util.List;

import com.betacom.ec.models.Alcolico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImmagineCantinaDTO {
	private Integer id;
	private String url;
	private CantinaDTO cantina;
}
