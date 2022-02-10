package com.eszterlukacs.fantasytodo.repository;

import com.eszterlukacs.fantasytodo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
