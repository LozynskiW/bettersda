package com.betterSDA.repo;

import com.betterSDA.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<AddressEntity, Long> {
}
