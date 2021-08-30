package com.backend.dto;

import java.util.List;

import com.backend.model.GameRoom;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class GetGameRoomReturnDto {
	
	@JsonProperty("quantity")
	int quantity;
	
	@JsonProperty("game-room-list")
	List<GameRoom> gameRoomList;
}
