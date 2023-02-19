package com.frankjiang.rewardsystem.service;

import com.frankjiang.rewardsystem.model.Rewards;
import com.frankjiang.rewardsystem.entity.Transaction;
import com.frankjiang.rewardsystem.model.RewardsAll;
import com.frankjiang.rewardsystem.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardService implements RewardServiceInterface{

    @Autowired
    RewardRepository rewardRepository;


    @Override
    public Map<Long, Map<String, Integer>> calculateRewards(List<Transaction> transactionList) {
        Map<Long, Map<String, Integer>> rewardsByCustomerMonth = new HashMap<>();

        for (Transaction transaction : transactionList) {
            Long customerId = transaction.getCustomer().getCustomerId();
            String month = transaction.getMonth();
            Double amount = transaction.getAmount();
            int points = calculatePoints(amount);

            if (!rewardsByCustomerMonth.containsKey(customerId)) {
                rewardsByCustomerMonth.put(customerId, new HashMap<>());
            }
            if (!rewardsByCustomerMonth.get(customerId).containsKey(month)) {
                rewardsByCustomerMonth.get(customerId).put(month, 0);
            }

            int currentPoints = rewardsByCustomerMonth.get(customerId).get(month);
            rewardsByCustomerMonth.get(customerId).put(month, currentPoints + points);
        }

        return rewardsByCustomerMonth;
    }

    @Override
    public Map<Long, Integer> getTotalPoints(List<Transaction> transactions){
        Map<Long, Integer> totalRewardsEach = new HashMap<>();
        for(Transaction transaction : transactions){
            Long customerId = transaction.getCustomer().getCustomerId();
            int totalPoints = calculatePoints(transaction.getAmount());
            if(!totalRewardsEach.containsKey(transaction.getCustomer().getCustomerId())) {
                totalRewardsEach.put(customerId, 0);
            }
            int currentPoints = totalRewardsEach.get(customerId);
            totalRewardsEach.put(customerId, currentPoints + totalPoints);
        }
        return totalRewardsEach;
    }

    @Override
    public List<Rewards> rewardShow(Map<Long, Integer> totalRewardEach){
        List<Rewards> rewards = new ArrayList<>();
        for(Long key : totalRewardEach.keySet()){
            Rewards r1 = new Rewards();
            r1.setCustomerId(key);
            r1.setTotalRewards(totalRewardEach.get(key));
            rewards.add(r1);
        }
        return rewards;
    }

    @Override
    public List<RewardsAll> rewardAllShow(Map<Long, Map<String, Integer>> rewardOfAll){
        List<RewardsAll> rewardsAllList = new ArrayList<>();
        for (Map.Entry<Long, Map<String, Integer>> entry : rewardOfAll.entrySet()) {
            RewardsAll rewardsAll = new RewardsAll();
            rewardsAll.setCustomerId(entry.getKey());
            Map<String, Integer> rewards = entry.getValue();
            rewardsAll.setRewardsByMonth(rewards);
            rewardsAllList.add(rewardsAll);
        }
        return rewardsAllList;
    }



    @Override
    public List<Transaction> getAllCustomers() {
        return rewardRepository.findAll();
    }

    private int calculatePoints(Double amount) {
        int res = 0;

        if (amount > 100) {
            res += (amount - 100) * 2;
        }

        if (amount > 50) {
            res += (Math.min(amount, 100) - 50);
        }
        return res;
    }
}
