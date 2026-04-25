package com.jay.springbootstarter.services;

import com.jay.springbootstarter.adapter.OrderItemConverter;
import com.jay.springbootstarter.dto.responses.OrderResponse;
import com.jay.springbootstarter.models.*;
import com.jay.springbootstarter.repositories.CartItemRepository;
import com.jay.springbootstarter.repositories.OrderRepository;
import com.jay.springbootstarter.repositories.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Data
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final OrderItemConverter orderItemConverter;

    public OrderResponse createOrder(Long userId) {

        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<CartItem> cartItems = cartItemRepository.findCartItemsByUser(user);
            if (!cartItems.isEmpty()) {
                Order savedOrder = saveOrderToDb(cartItems, user);
                cartItemRepository.deleteAll(cartItems);
                return orderItemConverter.orderModelToOrderResponse(savedOrder);
            } else {
                return null;
            }
        }
        return null;

    }

    private Order saveOrderToDb(List<CartItem> cartItems, User user) {
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.CONFIRMED)
                .totalAmount(totalPrice)
                .build();
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> orderItemConverter.cartItemToOrderItem(cartItem, order))
                .toList();
        order.setOrderItems(orderItems);
        return orderRepository.save(order);

    }

}
