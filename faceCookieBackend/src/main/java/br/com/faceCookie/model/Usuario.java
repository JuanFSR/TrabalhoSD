package br.com.faceCookie.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Usuario implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
}
