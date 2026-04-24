package com.jay.springbootstarter.repositories;

import com.jay.springbootstarter.models.CartItem;
import com.jay.springbootstarter.models.Product;
import com.jay.springbootstarter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product) ;

}
