package com.betterSDA.controller;

import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Office;
import com.betterSDA.model.dto.Person;
import com.betterSDA.service.OfficeService;
import com.betterSDA.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/office")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;
    private final PersonService personService;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Office getOffice() {
        return officeService.getOffice();
    }

    @GetMapping("/contact")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ModelAndView getOfficeData() {
        ModelAndView mav = new ModelAndView("contact");
        mav.addObject("office", officeService.getOffice());
        return mav;
    }
}
