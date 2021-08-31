package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.GameRoom;
import com.backend.model.Match;

public interface MatchRepository  extends JpaRepository<Match, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM public.match where match.player_id = :playerId and match.game_room_id = :gameRoomId ")
	Optional<Match> findMatch(@Param("playerId") Long playerId, @Param("gameRoomId") Long gameRoomId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM public.match where match.game_room_id = :gameRoomId ")
	List<Match> findByGameRoomId(@Param("gameRoomId") Long gameRoomId);
	
}
