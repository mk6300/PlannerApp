package com.plannerapp.service;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityName;

public interface PriorityService {
    void inItPriority();

    Priority findPriority(PriorityName priorityName);

}
