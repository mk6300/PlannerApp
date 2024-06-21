package com.plannerapp.vallidation;


import com.plannerapp.service.TaskService;
import com.plannerapp.service.impl.TaskServiceImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniquePerformerValidator implements ConstraintValidator<UniquePerformer, String> {

    private final TaskService taskService;

    public UniquePerformerValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
