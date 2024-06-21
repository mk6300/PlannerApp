package com.plannerapp.repo;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepo extends JpaRepository<Priority, Long> {

    Optional<Priority> findById(Long aLong);

    Optional<Priority> findByName (PriorityName priorityName);
}
