package com.eszterlukacs.fantasytodo.controller;

import com.eszterlukacs.fantasytodo.exception.ResourceNotFoundException;
import com.eszterlukacs.fantasytodo.model.Reward;
import com.eszterlukacs.fantasytodo.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/rewards/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable Long id) {
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reward does not exist with id: " + id));
        return ResponseEntity.ok(reward);
    }

    @PutMapping("/rewards/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable Long id, @RequestBody Reward rewardDetails){
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reward does not exist with id: " + id));

        reward.setTitle(rewardDetails.getTitle());
        reward.setDescription(rewardDetails.getDescription());
        reward.setPrice(rewardDetails.getPrice());

        Reward updateReward = rewardRepository.save(reward);
        return ResponseEntity.ok(updateReward);
    }

    @DeleteMapping("/rewards/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteReward(@PathVariable Long id){
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reward does not exist with id: " + id));
        rewardRepository.delete(reward);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
