
package com.homework_5.Homework5;

import com.homework_5.Homework5.model.Task;
import com.homework_5.Homework5.model.TaskStatus;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        // Блок предусловия
        Task task = new Task();

        // Блок действия
        task.setId(1L);
        task.setDescription("Complete");
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setCreatedAt(LocalDateTime.now());

        // Блок проверки действия
        assertNotNull(task.getId());
        assertEquals("Complete", task.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertNotNull(task.getCreatedAt());
    }

    @Test
    public void testTaskStatusFromString() {
        // Блок действия и блок проверки действия
        assertEquals(TaskStatus.NOT_STARTED, TaskStatus.fromString("NOT_STARTED"));
        assertEquals(TaskStatus.IN_PROGRESS, TaskStatus.fromString("IN_PROGRESS"));
        assertEquals(TaskStatus.COMPLETED, TaskStatus.fromString("COMPLETED"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskStatus.fromString("неверный статус");
        });
        assertTrue(exception.getMessage().contains("неверный статус"));
    }
}