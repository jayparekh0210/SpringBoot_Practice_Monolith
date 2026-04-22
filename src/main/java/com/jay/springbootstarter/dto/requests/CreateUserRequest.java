package com.jay.springbootstarter.dto.requests;

import com.jay.springbootstarter.dto.responses.AddressDTO;
import com.jay.springbootstarter.models.UserRole;
import lombok.Data;

import static com.jay.springbootstarter.models.UserRole.CUSTOMER;

@Data
public class CreateUserRequest {
    private String fName;
    private String lName;
    private String email;
    private String phoneNumber;
    private AddressDTO address;
}
