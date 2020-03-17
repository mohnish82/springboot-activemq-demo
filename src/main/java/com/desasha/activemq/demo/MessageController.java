package com.desasha.activemq.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired MessageService service;
	
	@PostMapping("/")
	@SendTo("appmsg")
	public void submitMessage(@RequestBody String msg) throws Exception {
		log.debug("sending - {}", msg);
		service.send(msg);
	}

}