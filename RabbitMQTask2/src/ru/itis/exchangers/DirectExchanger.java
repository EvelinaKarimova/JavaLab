package ru.itis.exchangers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectExchanger {
    private final static String EXCHANGE_NAME = "documents_direct_exchange";
    private final static String EXCHANGE_TYPE = "direct";
    private static final String STUDYING_APPLICATION_QUEUE = "studying_application_queue";
    private static final String LEAVING_APPLICATION_QUEUE = "leaving_application_queue";
    private static final String STUD_KEY = "study";
    private static final String LEAVE_KEY = "leave";


    public void exchange (String message, String key) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.queueBind(STUDYING_APPLICATION_QUEUE, EXCHANGE_NAME,STUD_KEY);
            channel.queueBind(LEAVING_APPLICATION_QUEUE, EXCHANGE_NAME, LEAVE_KEY);
            channel.basicPublish(EXCHANGE_NAME, key , null, message.getBytes());
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
