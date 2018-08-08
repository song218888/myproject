package com.tiancheng.rabbitmq.lesson01;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitMQConsumer {
	private final static String QUEUE_NAME = "queue_demo";
	private final static String IP_ADDRESS = "192.168.18.108";
	private final static int PORT = 5672;

	public static void main(String[] argv) throws Exception {

		Address[]addresses = new Address[]{new Address(IP_ADDRESS,PORT)};  
		  
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("guest");
	    factory.setPassword("guest");
	    
	    Connection connection = factory.newConnection(addresses);
	    
	    Channel channel = connection.createChannel();
	    channel.basicQos(64);


		 Consumer consumer = new DefaultConsumer(channel){
		    	@Override
		    	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
		    			throws IOException {
		    		// TODO Auto-generated method stub
		    		System.out.println("rece message: " + new String(body));
		    		
		    		try{
		    			TimeUnit.SECONDS.sleep(1);
		    		}catch (InterruptedException e) {
						// TODO: handle exception
		    			e.printStackTrace();
					}
		    		channel.basicAck(envelope.getDeliveryTag(), false);
		    	}
		    };
		   
		    channel.basicConsume(QUEUE_NAME, consumer);
		    TimeUnit.SECONDS.sleep(5);
		    channel.close();
		    connection.close();
	}
}
