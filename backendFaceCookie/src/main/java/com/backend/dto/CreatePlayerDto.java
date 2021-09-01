package com.backend.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerDto {
	
	@NotNull
    @JsonProperty("name")
    String name;
    
	@NotNull
    @JsonProperty("email")
    String email;
}
