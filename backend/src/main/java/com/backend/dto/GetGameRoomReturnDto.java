package com.backend.dto;

import java.util.List;

import com.backend.model.GameRoom;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGameRoomReturnDto {
	
	@JsonProperty("quantity")
	int quantity;
	
	@JsonProperty("list")
	List<GameRoomDto> gameRoomDto;
}
