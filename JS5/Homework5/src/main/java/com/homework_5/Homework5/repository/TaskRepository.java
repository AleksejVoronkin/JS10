package com.homework_5.Homework5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.homework_5.Homework5.model.Task;
import com.homework_5.Homework5.model.TaskStatus;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}