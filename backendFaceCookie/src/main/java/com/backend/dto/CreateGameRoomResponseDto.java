package com.backend.dto;

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
public class CreateGameRoomResponseDto {
    
	@JsonProperty("game-room")
    GameRoom gameRoom;
}
