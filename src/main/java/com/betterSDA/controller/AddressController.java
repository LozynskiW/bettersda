package com.betterSDA.controller;


import com.betterSDA.model.dto.Address;
import com.betterSDA.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    public void addAddress(@Valid Address address) {
        addressService.addAddress(address);
    }

    @PutMapping
    public void updateAddress(@Valid @RequestBody Address address) {
        addressService.updateAddress(address);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddressById(@RequestParam Long id) {
        addressService.deleteAddressById(id);
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }
}
