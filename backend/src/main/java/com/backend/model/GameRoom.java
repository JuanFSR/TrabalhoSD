package com.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game_room")
public class GameRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "nome")
	private String nome;
	
	@NotNull
	@Column(name = "max_players")
	private int maxPlayers;
	
	@OneToMany(targetEntity=Player.class, fetch=FetchType.EAGER)
	@Column(name = "players")
	private List<Player> players;
	
	@NotNull
	@Column(name = "is_visible")
	private boolean isVisible;
	
	@OneToOne(targetEntity=Player.class)
	@JoinColumn(name="winner")
	private Player winner;
}
