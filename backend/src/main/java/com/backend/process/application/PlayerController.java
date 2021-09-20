package com.backend.process.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.dto.CreatePlayerDto;
import com.backend.dto.CreatePlayerResponseDto;
import com.backend.service.PlayerService;

@Controller
@RequestMapping(path = "/player")
public class PlayerController {
	
	@Autowired 
	PlayerService playerService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/create")
	public ResponseEntity<CreatePlayerResponseDto> createPlayer(
			@Valid @RequestBody CreatePlayerDto dto) throws Exception {
		
		return ResponseEntity.ok(playerService.CreatePlayer(dto));
	}
}
