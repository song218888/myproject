package com.tiancheng.rabbitmq.lesson01;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitMQProducer {
	private final static String EXCHANGE_NAME = "exchange_hello";
	private final static String ROUTINT_KEY = "routingkey_demo";
	private final static String QUEUE_NAME = "queue_demo";
	private final static String IP_ADDRESS = "192.168.18.108";
	private final static int PORT = 5672;

	  public static void main(String[] argv) throws IOException,TimeoutException,InterruptedException {
	      
		Address[]addresses = new Address[]{new Address(IP_ADDRESS,PORT)};  
		  
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("guest");
	    factory.setPassword("guest");
	    
	    Connection connection = factory.newConnection(addresses);
	    
	    Channel channel = connection.createChannel();
	    channel.exchangeDeclare(EXCHANGE_NAME, "direct",true,false,null);
	    channel.queueDeclare(QUEUE_NAME, true,false,false,null);
	    channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTINT_KEY);
	    
	    String message = "Hello World";
	    
	    channel.basicPublish(EXCHANGE_NAME,ROUTINT_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
	    
	    channel.close();
	    connection.close();
	  }
}
