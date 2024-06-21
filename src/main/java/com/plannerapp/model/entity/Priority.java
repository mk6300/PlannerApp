package com.plannerapp.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "priority")
public class Priority extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated (EnumType.STRING)
    private PriorityName name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<Task> tasks;

    public Priority() {
    }

    public PriorityName getName() {
        return name;
    }

    public void setName(PriorityName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
