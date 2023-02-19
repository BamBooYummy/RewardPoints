package com.frankjiang.rewardsystem.controller;

import com.frankjiang.rewardsystem.entity.Transaction;
import com.frankjiang.rewardsystem.model.Rewards;
import com.frankjiang.rewardsystem.model.RewardsAll;
import com.frankjiang.rewardsystem.service.RewardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RewardController {

    @Autowired
    RewardServiceInterface rewardServiceInterface;

    @GetMapping("/rewards/all")
    public ResponseEntity<?> getAllRewards(){
        List<Transaction> allCustomers = rewardServiceInterface.getAllCustomers();
        Map<Long, Map<String, Integer>> rewardOfAll = rewardServiceInterface.calculateRewards(allCustomers);
        List<RewardsAll> rewardsAllList = rewardServiceInterface.rewardAllShow(rewardOfAll);
        return new ResponseEntity<>(rewardsAllList, HttpStatus.OK);
    }
    @GetMapping("/rewards/total")
    public ResponseEntity<?> getAllTotalRewards(){
        List<Transaction> allTransaction = rewardServiceInterface.getAllCustomers();
        Map<Long, Integer> totalRewardEach = rewardServiceInterface.getTotalPoints(allTransaction);
        List<Rewards> res = rewardServiceInterface.rewardShow(totalRewardEach);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
