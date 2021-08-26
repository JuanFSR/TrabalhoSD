package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.backend.service.dto.WebSocketDto;

import reactor.core.publisher.Mono;

public class WebSocketNotificationService {

	@Autowired
	@Qualifier("webClient")
	WebClient webClient;
	
	@Autowired
	SimpMessagingTemplate websocketTemplate;
	
	public void notificarTeste() {
		WebSocketDto mensagem = WebSocketDto.builder()
				.topico("teste")
				.payload("Hello Word")
				.build();
		
		webClient.post()
		.uri("/websocket/message-channel")
		.body(Mono.just(mensagem), WebSocketDto.class)
		.exchange()
		.block();
	}
	
}
