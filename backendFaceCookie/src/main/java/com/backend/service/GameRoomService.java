package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.backend.dto.CreateGameRoomDto;
import com.backend.dto.CreateGameRoomResponseDto;
import com.backend.dto.CreatePlayerResponseDto;
import com.backend.dto.GetGameRoomReturnDto;
import com.backend.dto.JoinGameRoomDto;
import com.backend.model.GameRoom;
import com.backend.model.Player;
import com.backend.model.enums.TipoNotificacaoEnum;
import com.backend.repository.GameRoomRepository;
import com.backend.repository.PlayerRepository;
import com.backend.service.dto.WebSocketDto;

@Service
public class GameRoomService {
	
	@Autowired
	GameRoomRepository gameRoomRepository;
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	WebSocketService webSocketService;
	
	public GetGameRoomReturnDto getAll() {
		List<GameRoom> listGameRoom = gameRoomRepository.findAllVisible();
		
		return GetGameRoomReturnDto.builder()
				.gameRoomList(listGameRoom)
				.quantity(listGameRoom.size())
				.build();
	}

	public CreateGameRoomResponseDto createGameRoom(CreateGameRoomDto dto) throws Exception {
		
		if (null != dto.getEmail()) {
			Optional<Player> optPlayer = playerRepository.findByEmail(dto.getEmail());
			
			if(optPlayer.isPresent()) {
				Player player = optPlayer.get();
				Optional<GameRoom> optGameRoom = gameRoomRepository.findActiveGame(player.getId());
				
				if(!optGameRoom.isPresent()) {
					List<Player> players = new ArrayList<>();
					players.add(player);
					
					GameRoom gameRoom = GameRoom.builder()
						.nome(dto.getNome())
						.maxPlayers(4)
						.isVisible(true)
						.build();
					
					gameRoomRepository.save(gameRoom);
					
					WebSocketDto messageDto = WebSocketDto.builder()
							.topico("geral")
							.payload(gameRoom)
							.build();
					
					webSocketService.notifyMessageChannel(messageDto);
					
					return CreateGameRoomResponseDto.builder()
							.gameRoom(gameRoom)
							.build();
					
				} else {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jogador já está em uma partida");
				}
			}
		}
		
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro");
	}
	
	public void joinGameRoom(JoinGameRoomDto dto, Long idGameRoom) throws Exception {
		
		if (null != dto.getEmail()) {
			Optional<Player> optPlayer = playerRepository.findByEmail(dto.getEmail());
			
			if(optPlayer.isPresent()) {
				Player player = optPlayer.get();
				Optional<GameRoom> optGameRoom = gameRoomRepository.findActiveGame(player.getId());
				
				if(!optGameRoom.isPresent()) {
					
					Optional<GameRoom> optGameRoom2 = gameRoomRepository.findById(idGameRoom);
					
					if(optGameRoom2.isPresent()) {
						GameRoom gameRoom = optGameRoom2.get();
						List<Player> players = gameRoom.getPlayers();
						
						if (players.size() < gameRoom.getMaxPlayers()) {
							players.add(player);
							
							gameRoomRepository.save(gameRoom);
							
							WebSocketDto messageDto = WebSocketDto.builder()
									.topico("game-room-" + String.valueOf(gameRoom.getId()))
									.tipo(TipoNotificacaoEnum.JOGADOR_ENTROU_SALA)
									.payload(gameRoom)
									.build();
							
							webSocketService.notifyMessageChannel(messageDto);
							
						} else {
							throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sala está cheia");
						}
					}
					
				} else {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jogador já está em uma partida");
				}
			}
		}
		
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro");
	}
}
