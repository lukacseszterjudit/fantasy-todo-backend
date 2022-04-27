package com.eszterlukacs.fantasytodo.controller;

import com.eszterlukacs.fantasytodo.model.Reward;
import com.eszterlukacs.fantasytodo.model.Task;
import com.eszterlukacs.fantasytodo.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class RewardController {

    @Autowired
    private RewardRepository rewardRepository;

    @GetMapping("/rewards")
    public List<Reward> getAllRewards(){
        return rewardRepository.findAll();
    }

    @PostMapping("/rewards")
    public Reward createReward(@RequestBody Reward reward){
        return rewardRepository.save(reward);
    }
}
