package com.frankjiang.rewardsystem.service;


import com.frankjiang.rewardsystem.model.Rewards;
import com.frankjiang.rewardsystem.entity.Transaction;
import com.frankjiang.rewardsystem.model.RewardsAll;

import java.util.List;
import java.util.Map;

public interface RewardServiceInterface {
    
    Map<Long, Map<String, Integer>> calculateRewards(List<Transaction> transactionList);

    List<Transaction> getAllCustomers();

    Map<Long, Integer> getTotalPoints(List<Transaction> allTransaction);

    List<Rewards> rewardShow(Map<Long, Integer> totalRewardEach);

    List<RewardsAll> rewardAllShow(Map<Long, Map<String, Integer>> rewardOfAll);
}
