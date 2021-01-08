package ru.itis.exchangers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutExchanger {
    private final static String EXCHANGE_NAME = "user_info";
    private final static String EXCHANGE_TYPE = "fanout";

    public void exchange(String message) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        } catch (TimeoutException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}