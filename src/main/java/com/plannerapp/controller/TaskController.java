package com.plannerapp.controller;

import com.plannerapp.model.AddTaskDTO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@RequestMapping("/tasks")
public interface TaskController {

        @GetMapping("/task-add")
        String addTask();

        @PostMapping("/task-add")
        String addTask(@Valid AddTaskDTO addTaskDTO, BindingResult result, RedirectAttributes redirectAttributes);

        @GetMapping("/assign-task/{id}")
        String assignTask(@PathVariable Long id);

        @GetMapping("/remove/{id}")
        String removeTask(@PathVariable Long id);

        @GetMapping("/return/{id}")
        String returnTask(@PathVariable Long id);
}
