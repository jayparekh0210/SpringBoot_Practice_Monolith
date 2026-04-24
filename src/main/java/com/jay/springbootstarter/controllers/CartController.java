package com.jay.springbootstarter.controllers;

import com.jay.springbootstarter.dto.requests.CartRequest;
import com.jay.springbootstarter.services.CartItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody CartRequest cartRequest
    ) {
        if (cartItemService.addToCart(userId, cartRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product out of stock or invalid product ID or user not found");

    }

    @DeleteMapping("/removeFromCart/{productId}")
    public ResponseEntity<String> removeFromCart(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String productId
    ) {
        if(cartItemService.removeFromCart(userId, Long.parseLong(productId))){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found in cart or user not found");
    }

    @DeleteMapping("/deleteCartItem/{productId}")
    public ResponseEntity<String> deleteCartItem(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String productId
    ) {
        if(cartItemService.deleteCartItem(userId, Long.parseLong(productId))){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found in cart or user not found");

    }
}
