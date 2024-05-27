package com.homework_5.Homework5.controller;

import com.homework_5.Homework5.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.homework_5.Homework5.model.Task;
import com.homework_5.Homework5.model.TaskStatus;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Задача не найдена по id: " + id));

        existingTask.setStatus(task.getStatus());
        return taskRepository.save(existingTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}