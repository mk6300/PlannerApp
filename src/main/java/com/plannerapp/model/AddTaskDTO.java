package com.plannerapp.model;

import com.plannerapp.model.entity.PriorityName;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AddTaskDTO {

    private Long id;

    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    @NotNull
    private String description;

    @NotNull(message = "You must select a priority!")
    private PriorityName priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Due Date must be in future!")
    private LocalDate dueDate;

    public AddTaskDTO() {
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public void setPriority(PriorityName priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public AddTaskDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}