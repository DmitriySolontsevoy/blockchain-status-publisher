package com.example.utils;

import com.example.dto.BlockDTO;
import com.example.dto.BlockchainDTO;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class ResponseParser {

    private final Gson gson;

    @Autowired
    public ResponseParser(Gson gson) {
        this.gson = gson;
    }

    @SneakyThrows
    public BlockDTO parseResponse(ResponseBody responseBody) {
        BlockchainDTO blockchainDTO = gson.fromJson(responseBody.string(), BlockchainDTO.class);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSSSS");

        String time = blockchainDTO.getTime().replace("T", " ").replace("Z", "");
        long unixTime = dateFormat.parse(time).getTime();

        return new BlockDTO(blockchainDTO.getHeight(), unixTime);
    }
}
