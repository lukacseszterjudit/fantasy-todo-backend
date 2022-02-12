package com.eszterlukacs.fantasytodo.controller;

import com.eszterlukacs.fantasytodo.model.Reward;
import com.eszterlukacs.fantasytodo.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RewardController {

    @Autowired
    private RewardRepository rewardRepository;

    @GetMapping("/rewards")
    public List<Reward> getAllRewards(){
        return rewardRepository.findAll();
    }
}