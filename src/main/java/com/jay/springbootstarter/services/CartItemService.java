package com.jay.springbootstarter.services;

import com.jay.springbootstarter.adapter.CartItemConverter;
import com.jay.springbootstarter.dto.requests.CartRequest;
import com.jay.springbootstarter.models.CartItem;
import com.jay.springbootstarter.models.Product;
import com.jay.springbootstarter.models.User;
import com.jay.springbootstarter.repositories.CartItemRepository;
import com.jay.springbootstarter.repositories.ProductRepository;
import com.jay.springbootstarter.repositories.UserRepository;
import com.jay.springbootstarter.validations.CartValidation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Data
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartValidation cartValidation;
    private final CartItemConverter cartItemConverter;

    public boolean addToCart(String userid, CartRequest cartRequest) {
        Product product = productRepository.findById(cartRequest.getProductId()).orElse(null);
        User user = userRepository.findById(Long.parseLong(userid)).orElse(null);

        if (cartValidation.isAddToCartValid(user, product, cartRequest)) {
            CartItem exsistingCartItem = cartItemRepository.findByUserAndProduct(user, product);
            if (exsistingCartItem != null) {
                if (cartValidation.AddToExistingCartValid(product, exsistingCartItem, cartRequest)) {
                    addMoreQuantityToCart(exsistingCartItem, cartRequest, product);
                } else {
                    return false;
                }
            } else {
                addNewCartItem(user, product, cartRequest);
            }
            return true;
        }
        return false;
    }

    public boolean deleteCartItem(String userid, Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(Long.parseLong(userid)).orElse(null);

        if (cartValidation.isRemoveFromCartValid(user, product)) {
            CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
            if (cartItem != null) {
                cartItemRepository.delete(cartItem);
                return true;
            }
        }
        return false;
    }

    public boolean removeFromCart(String userid, Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(Long.parseLong(userid)).orElse(null);

        if (cartValidation.isRemoveFromCartValid(user, product)) {
            CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
            if (cartItem != null) {
                if (cartItem.getQuantity() > 1) {
                    decreaseQuantityFromCart(cartItem, product);
                } else {
                    cartItemRepository.delete(cartItem);
                }
                return true;
            }
        }
        return false;
    }

    private void decreaseQuantityFromCart(CartItem cartItem, Product product) {
        cartItem.setQuantity(cartItem.getQuantity() - 1);
        cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        cartItemRepository.save(cartItem);
    }

    private void addMoreQuantityToCart(CartItem exsistingCartItem, CartRequest cartRequest, Product product) {
        exsistingCartItem.setQuantity(exsistingCartItem.getQuantity() + cartRequest.getQuantity());
        exsistingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(exsistingCartItem.getQuantity())));
        cartItemRepository.save(exsistingCartItem);
    }

    private void addNewCartItem(User user, Product product, CartRequest cartRequest) {
        CartItem cartItem = cartItemConverter.cartItemRequestToCartItemModel(cartRequest, user, product);
        cartItemRepository.save(cartItem);
    }
}
