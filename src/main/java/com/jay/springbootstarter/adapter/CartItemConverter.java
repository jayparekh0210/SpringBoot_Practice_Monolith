package com.jay.springbootstarter.adapter;

import com.jay.springbootstarter.dto.requests.CartRequest;
import com.jay.springbootstarter.dto.responses.CartResponse;
import com.jay.springbootstarter.models.CartItem;
import com.jay.springbootstarter.models.Product;
import com.jay.springbootstarter.models.User;
import com.jay.springbootstarter.repositories.ProductRepository;
import com.jay.springbootstarter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CartItemConverter {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    //Added for just in case we need it later
    public CartItem cartItemRequestToCartItemModel(CartRequest cartRequest, User user) {
        Product product = productRepository.findById(cartRequest.getProductId()).orElse(null);
        if(product == null){
            return null;
        }

        return CartItem.builder()
                .product(product)
                .user(user)
                .quantity(cartRequest.getQuantity())
                .price(product.getPrice().multiply(BigDecimal.valueOf(cartRequest.getQuantity())))
                .build();
    }

    public CartItem cartItemRequestToCartItemModel(CartRequest cartRequest, User user,Product product) {
        return CartItem.builder()
                .product(product)
                .user(user)
                .quantity(cartRequest.getQuantity())
                .price(product.getPrice().multiply(BigDecimal.valueOf(cartRequest.getQuantity())))
                .build();
    }

    public CartResponse cartItemModelToCartResponse(CartItem cartItem) {
        return CartResponse.builder()
                .product(cartItem.getProduct())
                .user(cartItem.getUser())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }


}
