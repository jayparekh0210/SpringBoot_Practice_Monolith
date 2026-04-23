package com.jay.springbootstarter.dto.responses;

import com.jay.springbootstarter.models.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.jay.springbootstarter.models.UserRole.CUSTOMER;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {

    private String id;
    private String fName;
    private String lName;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private AddressDTO address;
}
