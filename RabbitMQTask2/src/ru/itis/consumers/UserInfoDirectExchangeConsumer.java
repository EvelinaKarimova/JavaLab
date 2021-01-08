package ru.itis.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class UserInfoDirectExchangeConsumer {
    static String string;

    public static String getMessage(String queue) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.basicConsume(queue, false, (consumerTag, message) -> {
                string = Arrays.toString(message.getBody());
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                if (string == null) {
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                    throw new IOException();
                }
            }, consumerTag -> {});
            return string;
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }


    }
}
