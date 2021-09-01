package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.GameRoom;

public interface GameRoomRepository  extends JpaRepository<GameRoom, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM game_room WHERE is_visible = true ")
	List<GameRoom> findAllVisible();
	
	@Query(nativeQuery = true, value = "SELECT * FROM public.game_room, public.game_room_players WHERE game_room_players.players_id = :playerId and game_room.id = game_room_players.game_room_id  ")
	Optional<GameRoom> findActiveGame(@Param("playerId") Long playerId);
}
