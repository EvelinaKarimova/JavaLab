package ru.itis.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.generators.StudentApplicationForStudyingInITISGenerator;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class UserInfoFanoutExchangeConsumer {
    private final static String EXCHANGE_NAME = "user_info";
    private final static String EXCHANGE_TYPE = "fanout";
    private static String string;

    public static String getMessage() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "");
            channel.basicConsume(queue, false, (consumerTag, message) -> {
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                string = Arrays.toString(message.getBody());
            }, consumerTag -> {});
            return string;
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
