package com.frankjiang.rewardsystem.servicetest;

import com.frankjiang.rewardsystem.entity.Customer;
import com.frankjiang.rewardsystem.entity.Transaction;
import com.frankjiang.rewardsystem.model.RewardsAll;
import com.frankjiang.rewardsystem.repository.RewardRepository;
import com.frankjiang.rewardsystem.service.RewardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Mock
    private RewardRepository rewardRepository;

    @Test
    public void testCalculateRewards() {
        List<Transaction> transactions = new ArrayList<>();
        Customer customer1 = new Customer(1L, "John");
        Customer customer2 = new Customer(2L, "Jane");
        transactions.add(new Transaction(1L, customer1, "2022-01", 150.0));
        transactions.add(new Transaction(2L, customer1, "2022-02", 50.0));
        transactions.add(new Transaction(3L, customer2, "2022-01", 75.0));
        Map<Long, Map<String, Integer>> rewards = rewardService.calculateRewards(transactions);
        assertEquals(2, rewards.size());
        assertEquals(150, rewards.get(1L).get("2022-01").intValue());
        assertEquals(0, rewards.get(1L).get("2022-02").intValue());
        assertEquals(25, rewards.get(2L).get("2022-01").intValue());
    }

    @Test
    public void testGetTotalPoints() {
        List<Transaction> transactions = new ArrayList<>();
        Customer customer1 = new Customer(1L, "John");
        Customer customer2 = new Customer(2L, "Jane");
        transactions.add(new Transaction(1L, customer1, "2022-01", 150.0));
        transactions.add(new Transaction(2L, customer1, "2022-02", 50.0));
        transactions.add(new Transaction(3L, customer2, "2022-01", 75.0));
        Map<Long, Integer> totalPoints = rewardService.getTotalPoints(transactions);
        assertEquals(2, totalPoints.size());
        assertEquals(150, totalPoints.get(1L).intValue());
        assertEquals(25, totalPoints.get(2L).intValue());
    }
    @Test
    public void testRewardAllShow() {

        Map<Long, Map<String, Integer>> rewardOfAll = new HashMap<>();
        Map<String, Integer> rewards1 = new HashMap<>();
        rewards1.put("2022-01", 50);
        rewards1.put("2022-02", 100);
        rewardOfAll.put(1L, rewards1);

        Map<String, Integer> rewards2 = new HashMap<>();
        rewards2.put("2022-01", 150);
        rewards2.put("2022-02", 200);
        rewardOfAll.put(2L, rewards2);

        List<RewardsAll> rewardsAllList = rewardService.rewardAllShow(rewardOfAll);

        assertEquals(2, rewardsAllList.size());

        RewardsAll rewardsAll1 = rewardsAllList.get(0);
        assertEquals(1L, rewardsAll1.getCustomerId());
        assertEquals(50, (int) rewardsAll1.getRewardsByMonth().get("2022-01"));
        assertEquals(100, (int) rewardsAll1.getRewardsByMonth().get("2022-02"));

        RewardsAll rewardsAll2 = rewardsAllList.get(1);
        assertEquals(2L, rewardsAll2.getCustomerId());
        assertEquals(150, (int) rewardsAll2.getRewardsByMonth().get("2022-01"));
        assertEquals(200, (int) rewardsAll2.getRewardsByMonth().get("2022-02"));
    }
    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);

        Transaction transaction1 = new Transaction();
        transaction1.setCustomer(customer1);
        transaction1.setMonth("2022-01");
        transaction1.setAmount(200.0);

        Transaction transaction2 = new Transaction();
        transaction2.setCustomer(customer1);
        transaction2.setMonth("2022-02");
        transaction2.setAmount(100.0);

        Customer customer2 = new Customer();
        customer2.setCustomerId(2L);

        Transaction transaction3 = new Transaction();
        transaction3.setCustomer(customer2);
        transaction3.setMonth("2022-01");
        transaction3.setAmount(100.0);

        List<Transaction> expectedTransactions = Arrays.asList(transaction1, transaction2, transaction3);

        when(rewardRepository.findAll()).thenReturn(expectedTransactions);

        List<Transaction> actualTransactions = rewardService.getAllCustomers();

        assertEquals(expectedTransactions, actualTransactions);
    }
}
