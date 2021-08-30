package com.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class CreatePlayerResponseDto {
    
	@JsonProperty("response")
    String response;
}
