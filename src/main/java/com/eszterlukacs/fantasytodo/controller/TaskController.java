package com.eszterlukacs.fantasytodo.controller;

import com.eszterlukacs.fantasytodo.exception.ResourceNotFoundException;
import com.eszterlukacs.fantasytodo.model.Task;
import com.eszterlukacs.fantasytodo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id: " + id));
        return ResponseEntity.ok(task);
    }
}
