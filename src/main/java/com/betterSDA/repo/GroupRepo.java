package com.betterSDA.repo;

import com.betterSDA.model.entity.GroupEntity;
import com.betterSDA.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo  extends JpaRepository<GroupEntity, Long> {
}
