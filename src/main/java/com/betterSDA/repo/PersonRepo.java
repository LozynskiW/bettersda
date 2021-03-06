package com.betterSDA.repo;

import com.betterSDA.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepo extends JpaRepository<PersonEntity, UUID> {

    Optional<PersonEntity> findByEmail(String email);
    Optional<PersonEntity> findById(UUID id);

}
