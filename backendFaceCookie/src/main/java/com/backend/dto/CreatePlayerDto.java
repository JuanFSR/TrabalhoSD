package com.backend.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreatePlayerDto {
	
	@NotNull
    @JsonProperty("name")
    String name;
    
	@NotNull
    @JsonProperty("email")
    String email;
}
