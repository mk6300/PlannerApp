package com.plannerapp.service;

import com.plannerapp.model.AddTaskDTO;
import com.plannerapp.model.entity.Task;

import java.util.Set;

public interface TaskService {
    void addTask (AddTaskDTO addTaskDTO);

    Set<Task> findAllUnassignedTasks();

    Set<Task> findAllAssignedTasks(Long id);

    void assignTaskWithId(Long id, Long id1);
    void removeTaskById(Long id, Long id1);
    void returnTask(Long taskId, Long userId);
}
