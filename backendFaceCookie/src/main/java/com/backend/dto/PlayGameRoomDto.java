package com.backend.dto;

import javax.validation.constraints.NotNull;

import com.backend.model.enums.CardsEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayGameRoomDto {
    
	@NotNull
    @JsonProperty("email")
    String email;
	
	@NotNull
	@JsonProperty("move")
	CardsEnum move;
}
