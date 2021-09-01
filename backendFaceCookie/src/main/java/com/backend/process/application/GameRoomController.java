package com.backend.process.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.dto.CreateGameRoomDto;
import com.backend.dto.CreateGameRoomResponseDto;
import com.backend.dto.ExitGameRoomDto;
import com.backend.dto.GetGameRoomReturnDto;
import com.backend.dto.JoinGameRoomDto;
import com.backend.dto.PlayGameRoomDto;
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
	
	@PostMapping("/create")
	public ResponseEntity<CreateGameRoomResponseDto> createRoom(
			@Valid @RequestBody CreateGameRoomDto dto) throws Exception {
		
		return ResponseEntity.ok(gameRoomService.createGameRoom(dto));
	}
	
	@PostMapping("/join/{idGameRoom}")
	public ResponseEntity<?> joinRoom(
			@PathVariable(name = "idGameRoom", required = true) Long idGameRoom, 
			@Valid @RequestBody JoinGameRoomDto dto) throws Exception {
		
		gameRoomService.joinGameRoom(dto, idGameRoom);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/exit/{idGameRoom}")
	public ResponseEntity<?> exitRoom(
			@PathVariable(name = "idGameRoom", required = true) Long idGameRoom, 
			@Valid @RequestBody ExitGameRoomDto dto) throws Exception {
		
		gameRoomService.exitGameRoom(dto, idGameRoom);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/play/{idGameRoom}")
	public ResponseEntity<?> playRoom(
			@PathVariable(name = "idGameRoom", required = true) Long idGameRoom, 
			@Valid @RequestBody PlayGameRoomDto dto) throws Exception {
		
		gameRoomService.playGameRoom(dto, idGameRoom);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/result/{idGameRoom}")
	public ResponseEntity<?> resultGameRoom(
			@PathVariable(name = "idGameRoom", required = true) Long idGameRoom) throws Exception {
		
		gameRoomService.consultWinnerGameRoom(idGameRoom);
		return ResponseEntity.ok().build();
	}
}
