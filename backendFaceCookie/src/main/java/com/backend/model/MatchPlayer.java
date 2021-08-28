package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.model.enums.CardsEnum;

import lombok.Data;

//@Entity
@Data
@Table(name = "match_player")
public class MatchPlayer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "player_id")
	private Player player;
	
	@Column(name = "match_id")
	private Match match;
}
