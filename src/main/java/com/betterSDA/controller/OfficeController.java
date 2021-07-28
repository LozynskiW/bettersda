package com.betterSDA.controller;

import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Office;
import com.betterSDA.model.dto.Person;
import com.betterSDA.service.OfficeService;
import com.betterSDA.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/office")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;
    private final PersonService personService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAddress() {
        officeService.addOffice(Office.builder()
                .name("SDA")
                .admins(Person.builder()
                        .role(RoleEnum.ADMIN)
                        .email("sda@gmail.com")
                        .firstName("Software")
                        .lastName("Academy")
                        .phoneNumber("997997997")
                        .address(null)
                        .build())
                .build());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Office getOffice() {
        return officeService.getOffice();
    }
}
