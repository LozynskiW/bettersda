package com.betterSDA.service;


import com.betterSDA.model.dto.Office;
import com.betterSDA.repo.OfficeRepo;
import lombok.RequiredArgsConstructor;
import static com.betterSDA.service.DataConverter.toDto;
import static com.betterSDA.service.DataConverter.toEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OfficeService {

    private final OfficeRepo officeRepo;

    public void addOffice(Office office){
        officeRepo.save(toEntity(office));
    }


}
