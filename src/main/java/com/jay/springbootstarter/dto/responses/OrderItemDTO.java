package com.jay.springbootstarter.dto.responses;

import com.jay.springbootstarter.models.Order;
import com.jay.springbootstarter.models.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long id;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;

}
