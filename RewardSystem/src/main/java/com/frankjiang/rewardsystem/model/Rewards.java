package com.frankjiang.rewardsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rewards {

    private Long customerId;
    private int totalRewards;
}
