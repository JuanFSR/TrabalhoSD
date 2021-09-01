package com.backend.service.dto;

import com.backend.model.enums.TipoNotificacaoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author alisson e juan
 * Esta classe é a responsável por definir o formato de comunicação
 * entre o socket.
 *
 */
@Data
@Builder
@ToString
public class WebSocketDto {
	
	@JsonProperty("topico")
	private String topico;
	
	@JsonProperty("tipo")
	private TipoNotificacaoEnum tipo;
	
	@JsonProperty("payload")
	private Object payload;

}