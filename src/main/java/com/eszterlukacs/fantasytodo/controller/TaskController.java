package com.eszterlukacs.fantasytodo.controller;

import com.eszterlukacs.fantasytodo.exception.ResourceNotFoundException;
import com.eszterlukacs.fantasytodo.model.Task;
import com.eszterlukacs.fantasytodo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id: " + id));
        return ResponseEntity.ok(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id: " + id));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setGoldEarned(taskDetails.getGoldEarned());

        Task updateTask = taskRepository.save(task);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id: " + id));
        taskRepository.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
