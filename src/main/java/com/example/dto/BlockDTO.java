package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlockDTO {

    private long blockNumber;

    private long blockTime;
}
