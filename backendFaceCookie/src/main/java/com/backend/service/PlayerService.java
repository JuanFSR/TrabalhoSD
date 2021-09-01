package com.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.backend.dto.CreatePlayerDto;
import com.backend.dto.CreatePlayerResponseDto;
import com.backend.model.Player;
import com.backend.repository.PlayerRepository;

import lombok.NonNull;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	
	public CreatePlayerResponseDto CreatePlayer(@NonNull CreatePlayerDto dto) throws Exception {
		
		if (null != dto.getEmail() && null != dto.getName()) {
			// search for the player
			Optional<Player> optPlayer = playerRepository.findByEmail(dto.getEmail());
			
			if(optPlayer.isPresent()) {
				
				return CreatePlayerResponseDto.builder()
						.response("Seja bem vindo novamente!")
						.build();
				
			} else {
				Player player = Player.builder()
						.name(dto.getName())
						.email(dto.getEmail())
						.build();
				
				playerRepository.save(player);
				
				return CreatePlayerResponseDto.builder()
						.response("Jogador cadastrado com sucesso")
						.build();
			}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Preencha os valores corretamente");
		}
	}
}
