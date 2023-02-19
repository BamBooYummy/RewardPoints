package com.frankjiang.rewardsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardsAll {

    private Long customerId;

    private Map<String, Integer> RewardsByMonth;
}
