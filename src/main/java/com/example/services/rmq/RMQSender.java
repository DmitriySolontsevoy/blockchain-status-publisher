package com.example.services.rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RMQSender {

    @Value("${rabbitmq.host}")
    private String host;

    @SneakyThrows
    public void sendMessage(String message) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.host);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("Test", false, false, false, null);

        channel.basicPublish("", "Test", null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + message + "'");
    }
}
