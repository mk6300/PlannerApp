package com.plannerapp.service.impl;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.model.dto.TaskDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.TaskRepo;
import com.plannerapp.repo.UserRepo;
import com.plannerapp.service.PriorityService;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;
    private final UserService userService;
    private final PriorityService priorityService;


    public TaskServiceImpl(UserRepo userRepo, TaskRepo taskRepo, UserService userService, PriorityService priorityService) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.userService = userService;
        this.priorityService = priorityService;
    }

    @Override
    public void addTask(AddTaskDTO addTaskDTO) {
        Task task = new Task();
        Priority priority = this.priorityService.findPriority(addTaskDTO.getPriority());
        task.setPriority(priority);
        task.setDescription(addTaskDTO.getDescription());
        task.setDueDate(addTaskDTO.getDueDate());

        this.taskRepo.save(task);
    }

    @Override
    public Set<TaskDTO> findAllUnassignedTasks() {
        return taskRepo.findAllByAssignedToIsNull()
                .stream()
                .map(this::mapSongDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TaskDTO> findAllAssignedTasks(Long id) {
        return this.taskRepo.findAllByAssignedToId(id)
                .stream()
                .map(this::mapSongDTO)
                .collect(Collectors.toSet());
    }

    private TaskDTO mapSongDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority().getName().getValue());
        taskDTO.setDueDate(task.getDueDate());
        return taskDTO;
    }
    @Override
    public void assignTaskWithId(Long taskId, Long userId) {
        User currentUser = userService.findUserById(userId).orElse(null);
        Task taskById = taskRepo.findById(taskId).orElse(null);
        taskById.setAssignedTo(currentUser);
        taskRepo.save(taskById);
        currentUser.getAssignedTasks().add(taskById);

        userRepo.save(currentUser);
    }

    @Override
    public void removeTaskById(Long taskId, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        Task task1 = user.getAssignedTasks().stream().filter(e -> e.getId().equals(taskId)).findFirst().orElse(null);
        boolean removed = user.getAssignedTasks().remove(task1);

        userRepo.save(user);
        taskRepo.delete(task1);
    }

    @Override
    public void returnTask(Long taskId, Long userId) {
        Task task = taskRepo.findById(taskId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);
        Task task1 = user.getAssignedTasks().stream().filter(e -> e.getId().equals(taskId)).findFirst().orElse(null);
        boolean remove = user.getAssignedTasks().remove(task1);
        task.setAssignedTo(null);
        taskRepo.save(task);
        userRepo.save(user);
    }
}
