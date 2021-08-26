package com.backend.process.application;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.process.CreatedProcess;
import com.backend.process.domain.ProcessorService;
import com.backend.service.WebSocketService;
import com.backend.service.dto.WebSocketDto;


@CrossOrigin
@RestController
@RequestMapping(path = "/api")
public class ProcessController {

    private ProcessorService service;
    
    @Autowired
    WebSocketService webSocketService;

    @Autowired
    public ProcessController(ProcessorService service) {
        this.service = service;
    }

    @PutMapping
    public ResponseEntity<CreatedProcess> execute() {
        service.execute();
        return ResponseEntity.ok().body(new CreatedProcess(LocalDateTime.now()));
    }
    
	@PostMapping("/message-channel")
	public ResponseEntity<?> notifyMessageChannel(@RequestBody WebSocketDto message) throws Exception {
		this.webSocketService.notifyMessageChannel(message);
		return ResponseEntity.ok().build();
	}
	
	
}
