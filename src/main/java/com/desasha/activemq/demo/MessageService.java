package com.desasha.activemq.demo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class MessageService {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired JmsTemplate jms;
	@Autowired ObjectMapper jsonMapper;
	
	@JmsListener(destination = "appmsg")
	public void listenAndReject(@Payload String msg, 
						@Header(name = "JMSXDeliveryCount", defaultValue = "1") String attempt,
						Session session) throws JMSException {
		log.info("Message receieved (attempt #{}) -- {}", attempt, msg);
		session.rollback();
	}

	@JmsListener(destination = "appmsg1")
	@SendTo({"appmsg2"})
	public Greeting listener_1(@Payload Greeting greetings, Message raw) throws JMSException {
		log.info("Listener_1 -- {}, raw -- {}", greetings, raw);
		return new Greeting(greetings.getMessage());
	}
	
	@JmsListener(destination = "appmsg2")
	public void listener_2(@Payload Greeting greetings, Message raw) {
		log.info("Listener_2 -- {}, raw -- {}", greetings, raw);
	}

	public void send(final String msg) throws Exception {
		jms.convertAndSend("appmsg", new Greeting(msg));
	}
	
}
