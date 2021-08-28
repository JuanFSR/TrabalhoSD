package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.GameRoom;

public interface GameRoomRepository  extends JpaRepository<GameRoom, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM game_room WHERE is_visible = true ")
	List<GameRoom> findAllVisible();
}
