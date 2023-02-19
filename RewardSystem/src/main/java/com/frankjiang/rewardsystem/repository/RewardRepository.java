package com.frankjiang.rewardsystem.repository;

import com.frankjiang.rewardsystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Transaction, Long> {
}
