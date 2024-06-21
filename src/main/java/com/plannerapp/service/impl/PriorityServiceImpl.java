package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityName;
import com.plannerapp.repo.PriorityRepo;
import com.plannerapp.service.PriorityService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepo priorityRepo;

    public PriorityServiceImpl(PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }

    @Override
    public void inItPriority() {
        if (this.priorityRepo.count() != 0) {
            return;
        }

        Arrays.stream(PriorityName.values())
                .forEach(s -> {
                    Priority priority = new Priority();
                    priority.setName(s);
                    switch (s.getValue()) {
                        case "Urgent":
                            priority.setDescription("An urgent problem that blocks the system use until the issue is resolved.");
                            break;
                        case "Important":
                            priority.setDescription("A core functionality that your product is explicitly supposed to perform is compromised.");
                            break;
                        case "Low":
                            priority.setDescription("Should be fixed if time permits but can be postponed.");
                            break;
                    }

                    this.priorityRepo.save(priority);
                });



    }

    @Override
    public Priority findPriority(PriorityName priorityName) {
        return this.priorityRepo.findByName(priorityName).orElseThrow();
    }

//    @Override
//    public Priority findStyleByStyleName(PriorityName styleName) {
//        return this.priorityRepo.findByPriorityName(styleName).orElseThrow();
//    }
}
