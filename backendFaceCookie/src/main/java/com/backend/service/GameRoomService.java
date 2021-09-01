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
import com.backend.dto.ExitGameRoomDto;
import com.backend.dto.GameRoomDto;
import com.backend.dto.GetGameRoomReturnDto;
import com.backend.dto.JoinGameRoomDto;
import com.backend.dto.PlayGameRoomDto;
import com.backend.model.GameRoom;
import com.backend.model.Match;
import com.backend.model.Player;
import com.backend.model.enums.CardsEnum;
import com.backend.model.enums.TipoNotificacaoEnum;
import com.backend.repository.GameRoomRepository;
import com.backend.repository.MatchRepository;
import com.backend.repository.PlayerRepository;
import com.backend.service.dto.WebSocketDto;

@Service
public class GameRoomService {
	
	private int arrayGame[][] = {
			{2, 1, 0, 1, 0, 2}, 
			{0, 2, 1, 1, 1, 2}, 
			{1, 0, 2, 2, 1, 2}, 
			{0, 0, 2, 2, 0, 0}, 
			{0, 0, 0, 1, 2, 1},
			{2, 2, 2, 1, 0, 2}
	};
	
	@Autowired
	GameRoomRepository gameRoomRepository;
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	WebSocketService webSocketService;
	
	@Autowired
	MatchRepository matchRepository;
	
	public GetGameRoomReturnDto getAll() {
		List<GameRoom> listGameRoom = gameRoomRepository.findAllVisible();
		List<GameRoomDto> listGameRoomDto = new ArrayList<>();
		
		for (GameRoom gameRoom : listGameRoom) {
			GameRoomDto gameRoomDto = GameRoomDto.builder()
					.id(gameRoom.getId())
					.nome(gameRoom.getNome())
					.build();
			
			listGameRoomDto.add(gameRoomDto);
		}
		
		return GetGameRoomReturnDto.builder()
				.gameRoomDto(listGameRoomDto)
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
						.players(players)
						.build();
					
					gameRoomRepository.save(gameRoom);
					
					WebSocketDto messageDto = WebSocketDto.builder()
							.topico("canal-geral")
							.tipo(TipoNotificacaoEnum.SALA_CRIADA)
							.build();
					
					webSocketService.notifyMessageChannel(messageDto);
					
					return CreateGameRoomResponseDto.builder()
							.id(gameRoom.getId())
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
						
						if(gameRoom.isVisible()) {
							List<Player> players = gameRoom.getPlayers();
							
							if (players.size() < gameRoom.getMaxPlayers()) {
								players.add(player);
								
								gameRoomRepository.save(gameRoom);
								
								WebSocketDto messageDto = WebSocketDto.builder()
										.topico("game-room-" + String.valueOf(gameRoom.getId()))
										.tipo(TipoNotificacaoEnum.JOGADOR_ENTROU_SALA)
										.payload(players.size())
										.build();
								
								webSocketService.notifyMessageChannel(messageDto);
								
								return;
								
							} else {
								throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sala está cheia");
							}
						}
					}
					
				} else {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jogador já está em uma partida");
				}
			}
		}
		
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro");
	}
	
	public void exitGameRoom(ExitGameRoomDto dto, Long idGameRoom) throws Exception {
		
		if (null != dto.getEmail()) {
			Optional<Player> optPlayer = playerRepository.findByEmail(dto.getEmail());
			
			if(optPlayer.isPresent()) {
				Player player = optPlayer.get();
				Optional<GameRoom> optGameRoom = gameRoomRepository.findActiveGame(player.getId());
				
				if(optGameRoom.isPresent()) {
					
					Optional<GameRoom> optGameRoom2 = gameRoomRepository.findById(idGameRoom);
					
					if(optGameRoom2.isPresent()) {
						GameRoom gameRoom = optGameRoom2.get();
						List<Player> players = gameRoom.getPlayers();
						
						if (players.contains(player)) {
							players.remove(player);
							
							WebSocketDto messageDto;
							
							if(players.size() == 0) {
								gameRoom.setVisible(false);
								
								messageDto = WebSocketDto.builder()
										.topico("canal-geral")
										.tipo(TipoNotificacaoEnum.SALA_CRIADA)
										.build();
							} else {
								messageDto = WebSocketDto.builder()
										.topico("game-room-" + String.valueOf(gameRoom.getId()))
										.tipo(TipoNotificacaoEnum.JOGADOR_SAIU_SALA)
										.payload(players.size())
										.build();
							}
							
							gameRoomRepository.save(gameRoom);
							
							webSocketService.notifyMessageChannel(messageDto);
							
							return;
							
						} else {
							throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jogador não está na partida");
						}
					}
					
				} else {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jogador não está em uma partida");
				}
			}
		}
		
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro");
	}
	
	public void playGameRoom(PlayGameRoomDto dto, Long idGameRoom) throws Exception {
		
		if (null != dto.getEmail()) {
			Optional<Player> optPlayer = playerRepository.findByEmail(dto.getEmail());
			
			if(optPlayer.isPresent()) {
				Player player = optPlayer.get();
				Optional<GameRoom> optGameRoom = gameRoomRepository.findActiveGame(player.getId());
				
				if(optGameRoom.isPresent()) {
					GameRoom gameRoom = optGameRoom.get();
					List<Player> players = gameRoom.getPlayers();
					
					if(players.contains(player)) {
						Optional<Match> optMatch = matchRepository.findMatch(player.getId(), gameRoom.getId());
						Match match = null;
						
						if(optMatch.isPresent() && null != optMatch.get().getMove()) {
							
							throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jogador já jogou");
							
						} else if (optMatch.isPresent() && null == optMatch.get().getMove()) {
							match = optMatch.get();
							match.setMove(dto.getMove());
							
							
						} else {
							match = Match.builder()
									.gameRoom(gameRoom)
									.player(player)
									.move(dto.getMove())
									.build();
							
						}
						
						WebSocketDto messageDto = WebSocketDto.builder()
								.topico("game-room-" + String.valueOf(gameRoom.getId()))
								.tipo(TipoNotificacaoEnum.JOGADOR_JOGOU)
								.payload(gameRoom)
								.build();
						
						webSocketService.notifyMessageChannel(messageDto);
						
						matchRepository.save(match);
						return;
						
					}
					
				}
			
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jogador não está na partida");
				
			}
		}
		
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro");
	}

	public int consultWinner(int index1, int index2, List<Match> matches) {
		int value = arrayGame[matches.get(index1).getMove().ordinal()][matches.get(index2).getMove().ordinal()];
		
		if (value == 0) {
			return index2;
		} else if (value == 1) {
			return index1;
		}
		
		return -1;
	}
	
	public void consultWinnerGameRoom(Long idGameRoom) throws Exception {
		Optional<GameRoom> optGameRoom = gameRoomRepository.findById(idGameRoom);
		
		if(optGameRoom.isPresent()) {
			GameRoom gameRoom = optGameRoom.get();
			
			if(gameRoom.isVisible()) {
				List<Match> matches = matchRepository.findByGameRoomId(gameRoom.getId());
				
				if(matches.size() == gameRoom.getMaxPlayers()) {
					int winner = -1;
					CardsEnum result1 = matches.get(0).getMove();
					CardsEnum result2 = matches.get(1).getMove();
					CardsEnum result3 = matches.get(2).getMove();
					CardsEnum result4 = matches.get(3).getMove();
					
					if (result1.equals(result2)) {
						winner = consultWinner(2, 3, matches);
					} else if (result1.equals(result3)) {
						winner = consultWinner(2, 4, matches);
					} else if (result1.equals(result4)) {
						winner = consultWinner(2, 3, matches);						
					} else if (result2.equals(result3)) {
						winner = consultWinner(1, 4, matches);						
					} else if (result2.equals(result4)) {
						winner = consultWinner(1, 3, matches);						
					} else if (result3.equals(result4)) {
						winner = consultWinner(1, 2, matches);						
					} 
					
					WebSocketDto messageGeralDto;
					WebSocketDto messageWinnerDto;
					gameRoom.setVisible(false);

					if (winner == -1) {
						gameRoom.setWinner(null);
						
						messageGeralDto = WebSocketDto.builder()
								.topico("game-room-" + String.valueOf(gameRoom.getId()))
								.tipo(TipoNotificacaoEnum.RESULTADO)
								.payload(0)
								.build();
					} else {
						Player PlayerWinner = matches.get(winner).getPlayer();
						gameRoom.setWinner(PlayerWinner);
						
						messageWinnerDto = WebSocketDto.builder()
								.topico(PlayerWinner.getEmail())
								.tipo(TipoNotificacaoEnum.RESULTADO)
								.payload(1)
								.build();
						
						webSocketService.notifyMessageChannel(messageWinnerDto);
						
						messageGeralDto = WebSocketDto.builder()
								.topico("game-room-" + String.valueOf(gameRoom.getId()))
								.tipo(TipoNotificacaoEnum.RESULTADO)
								.payload(1)
								.build();
						
					}
					
					gameRoomRepository.save(gameRoom);
					
					webSocketService.notifyMessageChannel(messageGeralDto);
						
					
					
					return;
					
				} else {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ainda faltam jogadores para jogar");
				}
				
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sala já finalizada");
			}
			
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sala não encontrada");
		}
	}
	
	public void initGameRoom(Long idGameRoom) throws Exception {
		Optional<GameRoom> optGameRoom = gameRoomRepository.findById(idGameRoom);
		
		if(optGameRoom.isPresent()) {
			GameRoom gameRoom = optGameRoom.get();
			
			if(gameRoom.isVisible()) {
				WebSocketDto messagelDto = WebSocketDto.builder()
						.topico("game-room-" + String.valueOf(gameRoom.getId()))
						.tipo(TipoNotificacaoEnum.JOGO_INICIOU)
						.build();
				
				webSocketService.notifyMessageChannel(messagelDto);
			}
		}
	}
}
