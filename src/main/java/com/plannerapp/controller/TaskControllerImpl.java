package com.plannerapp.controller;


import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class TaskControllerImpl implements TaskController {

    private final LoggedUser loggedUser;
    private final TaskService taskService;

    public TaskControllerImpl(LoggedUser loggedUser, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
    }

    @Override
    public String addTask() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "task-add";
    }

    @Override
    public String addTask(AddTaskDTO addtaskDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addTaskDTO", addtaskDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addTaskDTO", result);
            return "redirect:/tasks/task-add";
        }

        this.taskService.addTask(addtaskDTO);
        return "redirect:/home";
    }

    @Override
    public String assignTask(Long id) {
        taskService.assignTaskWithId(id, loggedUser.getId());
        return "redirect:/home";
    }

    @Override
    public String removeTask(Long id) {
        taskService.removeTaskById(id, loggedUser.getId());

        return "redirect:/home";
    }

    @Override
    public String returnTask(Long id) {
        taskService.returnTask(id, loggedUser.getId());
        return "redirect:/home";
    }

    @ModelAttribute
    public AddTaskDTO addTaskDTO() {
        return new AddTaskDTO();
    }
}
