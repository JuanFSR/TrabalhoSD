package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	Optional<Player> findByEmail(String email);
}
