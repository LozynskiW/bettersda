package com.betterSDA.repo;

import com.betterSDA.model.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<TeamEntity, String> {
}
