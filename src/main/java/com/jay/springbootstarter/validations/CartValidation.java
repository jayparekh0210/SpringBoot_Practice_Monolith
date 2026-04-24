package com.jay.springbootstarter.validations;

import com.jay.springbootstarter.dto.requests.CartRequest;
import com.jay.springbootstarter.models.CartItem;
import com.jay.springbootstarter.models.Product;
import com.jay.springbootstarter.models.User;
import org.springframework.stereotype.Component;

@Component
public class CartValidation {
    public boolean isAddToCartValid(User user, Product product, CartRequest cartRequest) {
        return user != null && product != null && product.getStockQuantity() >= cartRequest.getQuantity();
    }

    public boolean AddToExistingCartValid(Product product, CartItem existingCartItem, CartRequest cartRequest) {
        return product != null && product.getStockQuantity() >= cartRequest.getQuantity() + existingCartItem.getQuantity();
    }

    public boolean isRemoveFromCartValid(User user, Product product) {
        return user != null && product != null;
    }
}

