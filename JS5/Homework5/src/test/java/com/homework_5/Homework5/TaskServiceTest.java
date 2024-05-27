package com.homework_5.Homework5;

import com.homework_5.Homework5.service.TaskService;
import com.homework_5.Homework5.model.Task;
import com.homework_5.Homework5.model.TaskStatus;
import com.homework_5.Homework5.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testAddTask() {
        // Блок предусловия
        Task task = new Task();
        task.setDescription("New Task");
        task.setStatus(TaskStatus.NOT_STARTED);

        // Блок действия
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task savedTask = taskService.addTask(task);

        // Блок проверки действия
        assertNotNull(savedTask);
        verify(taskRepository).save(task);
    }

    @Test
    public void testGetAllTasks() {
        // Блок предусловия
        Task task1 = new Task();
        task1.setStatus(TaskStatus.NOT_STARTED);
        Task task2 = new Task();
        task2.setStatus(TaskStatus.COMPLETED);
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskRepository.findAll()).thenReturn(tasks);

        // Блок действия
        List<Task> retrievedTasks = taskService.getAllTasks();

        // Блок проверки действия
        assertNotNull(retrievedTasks);
        assertEquals(2, retrievedTasks.size());
        verify(taskRepository).findAll();
    }

    @Test
    public void testUpdateTaskStatus() {
        // Блок предусловия
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setStatus(TaskStatus.NOT_STARTED);
        Task updatedInfo = new Task();
        updatedInfo.setStatus(TaskStatus.COMPLETED);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(existingTask);

        // Блок действия
        Task updatedTask = taskService.updateTaskStatus(1L, updatedInfo);

        // Блок проверки действия
        assertNotNull(updatedTask);
        assertEquals(TaskStatus.COMPLETED, updatedTask.getStatus());
        verify(taskRepository).findById(1L);
        verify(taskRepository).save(existingTask);
    }

    @Test
    public void testDeleteTask() {
        // Блок предусловия
        Long taskId = 1L;
        doNothing().when(taskRepository).deleteById(taskId);

        // Блок действия
        taskService.deleteTask(taskId);

        // Блок проверки действия
        verify(taskRepository).deleteById(taskId);
    }
}