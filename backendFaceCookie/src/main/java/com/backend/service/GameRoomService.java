package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.GetGameRoomReturnDto;
import com.backend.model.GameRoom;
import com.backend.repository.GameRoomRepository;

@Service
public class GameRoomService {
	
	@Autowired
	GameRoomRepository gameRoomRepository;
	
	public GetGameRoomReturnDto getAll() {
		List<GameRoom> listGameRoom = gameRoomRepository.findAllVisible();
		
		return GetGameRoomReturnDto.builder()
				.gameRoomList(listGameRoom)
				.quantity(listGameRoom.size())
				.build();
	}
}
