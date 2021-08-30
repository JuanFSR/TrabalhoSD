package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.backend.service.dto.WebSocketDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;

@Service
public class WebSocketService {
	
	@Autowired
	SimpMessagingTemplate websocketTemplate;
	
	@Autowired
	ObjectMapper mapper;
	
	public void notifyMessageChannel(@NonNull WebSocketDto message) throws Exception {
		websocketTemplate.convertAndSend("/" + message.getTopico(), mapper.writeValueAsString(message));
	}
	
}
