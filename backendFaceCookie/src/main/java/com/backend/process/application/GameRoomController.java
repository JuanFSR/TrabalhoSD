package com.backend.process.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.dto.CreatePlayerDto;
import com.backend.dto.CreatePlayerResponseDto;
import com.backend.dto.GetGameRoomReturnDto;
import com.backend.service.GameRoomService;

@Controller
@RequestMapping(path = "/game-room")
public class GameRoomController {
	
	@Autowired
	GameRoomService gameRoomService;
	
	@GetMapping("/")
	public ResponseEntity<GetGameRoomReturnDto> getRooms() throws Exception {
		
		return ResponseEntity.ok(gameRoomService.getAll());
	}
}
