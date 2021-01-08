package ru.itis.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class UserInfoTopicExchangeConsumer {
    static String string;
    private final static String EXCHANGE_NAME = "topic_exchange";

    public static String getMessage(String routing) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, routing);
            channel.basicConsume(queueName, false, (consumerTag, message) -> {
                string = Arrays.toString(message.getBody());
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                if (string == null) {
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                    throw new IOException();
                }
            }, consumerTag -> {});
            return string;
        } catch (TimeoutException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
