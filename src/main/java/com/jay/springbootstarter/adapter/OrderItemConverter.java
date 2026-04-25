package com.jay.springbootstarter.adapter;

import com.jay.springbootstarter.dto.responses.OrderItemDTO;
import com.jay.springbootstarter.dto.responses.OrderResponse;
import com.jay.springbootstarter.models.CartItem;
import com.jay.springbootstarter.models.Order;
import com.jay.springbootstarter.models.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    public OrderItem cartItemToOrderItem(CartItem cartItem, Order order) {
        return OrderItem.builder()
                .product(cartItem.getProduct())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .order(order)
                .build();
    }

    public OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .product(orderItem.getProduct())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .subtotal(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .build();
    }

    public OrderResponse orderModelToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .orderItems(order.getOrderItems().stream()
                        .map(this::orderItemToOrderItemDTO)
                        .toList())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
