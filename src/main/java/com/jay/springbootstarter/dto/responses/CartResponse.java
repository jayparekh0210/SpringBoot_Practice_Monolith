package com.jay.springbootstarter.dto.responses;

import com.jay.springbootstarter.models.Product;
import com.jay.springbootstarter.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private User user;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
}
