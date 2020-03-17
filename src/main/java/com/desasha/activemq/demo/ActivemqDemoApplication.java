package com.desasha.activemq.demo;

import org.apache.activemq.RedeliveryPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class ActivemqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqDemoApplication.class, args);
	}
	
	@Bean
    public ActiveMQConnectionFactoryCustomizer configureRedeliveryPolicy() {
        return connectionFactory ->
        {
            RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
            redeliveryPolicy.setInitialRedeliveryDelay(10000);
            redeliveryPolicy.setRedeliveryDelay(10000);
            redeliveryPolicy.setUseExponentialBackOff(true);
            redeliveryPolicy.setBackOffMultiplier(2);
            redeliveryPolicy.setMaximumRedeliveries(2);
            
            connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        };
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
      MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
      converter.setTargetType(MessageType.TEXT);
      converter.setTypeIdPropertyName("_type");
      return converter;
    }
}