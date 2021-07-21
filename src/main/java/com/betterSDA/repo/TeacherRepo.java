package com.betterSDA.repo;

import com.betterSDA.model.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<TeacherEntity, Long> {
}
