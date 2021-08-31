package com.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGameRoomDto {
    
	@JsonProperty("nome")
    String nome;

	@JsonProperty("email")
	String email;
}
