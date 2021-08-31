package com.backend.dto;

import com.backend.model.GameRoom;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class CreateGameRoomResponseDto {
    
	@JsonProperty("game-room")
    GameRoom gameRoom;
}
