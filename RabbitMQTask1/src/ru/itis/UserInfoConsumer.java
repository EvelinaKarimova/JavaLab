package ru.itis;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class UserInfoConsumer {
    private final static String EXCHANGE_NAME = "user_info";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "");
            channel.basicConsume(queue, false, (consumerTag, message) -> {
                System.out.println(Arrays.toString(message.getBody()));
                String string = new String(message.getBody());
                String str[] = string.split("/");
                StudentApplicationForStudyingInITISGenerator generator =
                        new StudentApplicationForStudyingInITISGenerator(str[0], str[1], str[2], str[3], str[4]);
                generator.generatePDF();
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}