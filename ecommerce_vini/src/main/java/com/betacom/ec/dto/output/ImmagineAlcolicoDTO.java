package com.betacom.ec.dto.output;

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
public class ImmagineAlcolicoDTO {
	private Integer id;
	private String url;
	private Integer idAlcolico;
}
