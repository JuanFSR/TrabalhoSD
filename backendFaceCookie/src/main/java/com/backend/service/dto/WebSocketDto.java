package com.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class WebSocketDto {
	
	@JsonProperty("topico")
	private String topico;
	
	//@JsonProperty("tipo")
	//private TipoNotificacaoEnum tipo;
	
	@JsonProperty("payload")
	private Object payload;

}