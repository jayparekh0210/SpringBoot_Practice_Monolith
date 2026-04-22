package com.jay.springbootstarter.dto.responses;

import com.jay.springbootstarter.models.UserRole;
import lombok.Builder;
import lombok.Data;

import static com.jay.springbootstarter.models.UserRole.CUSTOMER;

@Data
@Builder
public class CreateUserResponse {

    private String id;
    private String fName;
    private String lName;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private AddressDTO address;
}
