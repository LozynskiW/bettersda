package com.betterSDA.repo;

import com.betterSDA.model.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepo extends JpaRepository<OfficeEntity, Long> {
}
