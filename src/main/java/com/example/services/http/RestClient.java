package com.example.services.http;

import com.example.dto.BlockDTO;
import com.example.services.rmq.RMQSender;
import com.example.utils.MessageSerializer;
import com.example.utils.ResponseParser;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RestClient {

    private final OkHttpClient okHttpClient;
    private final ResponseParser responseParser;
    private final RMQSender rmqSender;
    private final MessageSerializer messageSerializer;

    @Autowired
    public RestClient(OkHttpClient okHttpClient, ResponseParser responseParser, RMQSender rmqSender, MessageSerializer messageSerializer) {
        this.okHttpClient = okHttpClient;
        this.responseParser = responseParser;
        this.rmqSender = rmqSender;
        this.messageSerializer = messageSerializer;
    }

    @SneakyThrows
    public void getBlockchainStatus() {
        Request request = new Request.Builder()
                .url("http://api.blockcypher.com/v1/beth/test")
                .method("GET", null)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        BlockDTO newBlock = responseParser.parseResponse(Objects.requireNonNull(response.body()));

        this.rmqSender.sendMessage(messageSerializer.serializeMessage(newBlock));
    }
}
