package com.example.schedulers;

import com.example.services.http.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BlockchainStatusScheduler {

    private final RestClient restClient;

    @Autowired
    public BlockchainStatusScheduler(RestClient restClient) {
        this.restClient = restClient;
    }

    @Scheduled(fixedDelay = 10000)
    public void checkStatus() {
        restClient.getBlockchainStatus();
    }
}
