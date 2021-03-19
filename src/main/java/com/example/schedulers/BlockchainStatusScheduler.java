package com.example.schedulers;

import com.example.services.http.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BlockchainStatusScheduler {

    private final RestClient restClient;

    @Autowired
    public BlockchainStatusScheduler(RestClient restClient) {
        this.restClient = restClient;
    }

    @Scheduled(fixedDelayString = "${poll.interval}")
    public void pollBlock() {
        restClient.getBlockchainStatus();
    }
}
