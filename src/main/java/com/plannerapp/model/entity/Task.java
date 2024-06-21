package com.plannerapp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name="tasks")
public class Task extends BaseEntity{
    @Column (nullable = false)
    private String description;

    @Column (nullable = false)
    private LocalDate dueDate;

    @ManyToOne
    private Priority priority;

    @ManyToOne(fetch = FetchType.EAGER)
    private User assignedTo;

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Task() {
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
