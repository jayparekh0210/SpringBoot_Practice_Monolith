package com.jay.springbootstarter.adapter;

import com.jay.springbootstarter.dto.responses.AddressDTO;
import com.jay.springbootstarter.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {
    public AddressDTO addressModelToAddressResponse(Address address) {
        return AddressDTO.builder()
                .country(address.getCountry())
                .state(address.getState())
                .street(address.getStreet())
                .zipcode(address.getZipcode())
                .build();
    }

    public Address addressDTOToAddressModel(AddressDTO addressDTO) {
        return Address.builder()
                .country(addressDTO.getCountry())
                .state(addressDTO.getState())
                .street(addressDTO.getStreet())
                .zipcode(addressDTO.getZipcode())
                .build();
    }
}
