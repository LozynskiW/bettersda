package com.betterSDA.repo;

import com.betterSDA.model.dto.Person;
import com.betterSDA.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<PersonEntity, Long> {
}
