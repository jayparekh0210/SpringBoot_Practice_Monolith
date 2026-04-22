package com.jay.springbootstarter.controllers;

import com.jay.springbootstarter.dto.requests.CreateUserRequest;
import com.jay.springbootstarter.dto.responses.CreateUserResponse;
import com.jay.springbootstarter.models.User;
import com.jay.springbootstarter.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CreateUserResponse>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body( userService.getAllUser());
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest userRequest){
        CreateUserResponse user1 = userService.addUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CreateUserResponse> getUser(@PathVariable Long id){
        Optional<CreateUserResponse> createUserResponse = userService.fetchUser(id);
        return createUserResponse.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable Long id, @RequestBody CreateUserRequest updatedUser){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,updatedUser));
    }
}
