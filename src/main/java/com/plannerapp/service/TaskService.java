package com.plannerapp.service;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.model.dto.TaskDTO;
import com.plannerapp.model.entity.Task;

import java.util.Set;

public interface TaskService {
    void addTask (AddTaskDTO addTaskDTO);

    Set<TaskDTO> findAllUnassignedTasks();

    Set<TaskDTO> findAllAssignedTasks(Long id);

    void assignTaskWithId(Long id, Long id1);
    void removeTaskById(Long id, Long id1);
    void returnTask(Long taskId, Long userId);
}
