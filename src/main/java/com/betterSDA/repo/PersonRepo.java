package com.betterSDA.repo;

import com.betterSDA.model.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
}
