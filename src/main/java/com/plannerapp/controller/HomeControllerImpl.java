package com.plannerapp.controller;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController{

    private final LoggedUser loggedUser;
    private final UserService userService;

    private final TaskService taskService;

    public HomeControllerImpl(LoggedUser loggedUser, UserService userService, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = userService.findUserById(loggedUser.getId()).orElse(null);
        model.addAttribute("currentUserInfo", user);

        Set<Task> AssignedToIsNull = taskService.findAllUnassignedTasks();
        Set<Task> allAssignedById =taskService.findAllAssignedTasks(loggedUser.getId());


        model.addAttribute("AlUnassignedTasks", AssignedToIsNull);
        model.addAttribute("CountUnassigned", AssignedToIsNull.size());
        model.addAttribute("allAssignedById",allAssignedById);


        return "home";
    }
}
