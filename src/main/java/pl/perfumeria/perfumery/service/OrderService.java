package pl.perfumeria.perfumery.service;

import pl.perfumeria.perfumery.domain.Order;
import pl.perfumeria.perfumery.domain.OrderStatus;

import java.util.Optional;

public interface OrderService {

    void placeOrder();

    Optional<Order> findOrderById(Long id);

    void updateOrderStatus(Long id, OrderStatus newStatus);
}