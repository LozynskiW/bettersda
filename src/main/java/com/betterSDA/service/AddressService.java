package com.betterSDA.service;

import com.betterSDA.model.dto.Address;
import com.betterSDA.model.entity.AddressEntity;
import com.betterSDA.repo.AddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepo addressRepo;

    public void addAddress(Address address) {
        addressRepo.save(AddressEntity.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .build());
    }

    public void updateAddress(Address address) {
        addressRepo.save(AddressEntity.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .id(address.getId())
                .build());
    }

    public void deleteAddressById (Long id) {
        addressRepo.deleteById(id);
    }

    public List<Address> getAllAddresses() {
        return addressRepo.findAll().stream().map(this::toAddress)
        .collect(Collectors.toList());
    }

    public Address toAddress(AddressEntity entity) {
        return Address.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .zipCode(entity.getZipCode())
                .country(entity.getCountry())
                .build();
    }

    public Address getAddressById(Long id) {

        Optional<AddressEntity> addressEntity = addressRepo.findById(id);

        if (addressEntity.isPresent()) return toAddress(addressEntity.get());

        throw new NoSuchElementException("No address found in database");

    }


}
