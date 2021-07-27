package com.betterSDA.service;

import com.betterSDA.model.dto.Address;
import com.betterSDA.model.entity.AddressEntity;
import com.betterSDA.repo.AddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.betterSDA.service.DataConverter.toDto;
import static com.betterSDA.service.DataConverter.toEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepo addressRepo;

    public void addAddress(Address address) {
        addressRepo.save(toEntity(address));
    }

    public void updateAddress(Address address) {
        addressRepo.save(toEntity(address));
    }

    public void deleteAddressById (Long id) {
        addressRepo.deleteById(id);
    }

    public List<Address> getAllAddresses() {
        return addressRepo.findAll().stream().map(DataConverter::toDto)
        .collect(Collectors.toList());
    }



    public Address getAddressById(Long id) {

        Optional<AddressEntity> addressEntity = addressRepo.findById(id);

        if (addressEntity.isPresent()) return toDto(addressEntity.get());

        throw new NoSuchElementException("No address found in database");

    }


}
