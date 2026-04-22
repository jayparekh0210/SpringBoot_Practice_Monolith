package com.jay.springbootstarter.services;

import com.jay.springbootstarter.adapter.UserConverter;
import com.jay.springbootstarter.dto.requests.CreateUserRequest;
import com.jay.springbootstarter.dto.responses.CreateUserResponse;
import com.jay.springbootstarter.models.User;
import com.jay.springbootstarter.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class UserService {
    private UserRepository userRepository;
    private UserConverter userConverter;

    public List<CreateUserResponse> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::userModelToUserResponse)
                .collect(Collectors.toList());
    }

    public CreateUserResponse addUser(CreateUserRequest userRequest) {
        return userConverter.userModelToUserResponse(userConverter.userRequestToUserModel(userRequest));
    }

    public Optional<CreateUserResponse> fetchUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return Optional.ofNullable(userConverter.userModelToUserResponse(user));
        } else {
            return Optional.empty();
        }
    }

    public boolean updateUser(Long id, CreateUserRequest createUserRequest) {
        User updatedUser = userConverter.userRequestToUserModel(createUserRequest);
        return userRepository.findById(id).map(existinguser -> {
            existinguser.setFName(updatedUser.getFName());
            existinguser.setLName(updatedUser.getLName());
            existinguser.setEmail(updatedUser.getEmail());
            existinguser.setPhoneNumber(updatedUser.getPhoneNumber());
            existinguser.setRole(updatedUser.getRole());
            userRepository.save(existinguser);
            return true;
        }).orElse(false);
    }


}
