package com.example.utils;

import com.example.dto.BlockDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSerializer {

    private final Gson gson;

    @Autowired
    public MessageSerializer(Gson gson) {
        this.gson = gson;
    }

    public String serializeMessage(BlockDTO message) {
        return gson.toJson(message);
    }
}
