package pl.perfumeria.perfumery.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.perfumeria.perfumery.domain.*;
import pl.perfumeria.perfumery.repository.OrderRepository;
import pl.perfumeria.perfumery.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public OrderServiceImpl(CartService cartService, OrderRepository orderRepository, UserRepository userRepository, EmailService emailService) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;

    }

    @Override
    @Transactional
    public void placeOrder() {

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);
        order.setTotalPrice(cartService.getTotalPrice());

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(currentUser);

        List<OrderItem> orderItems = new ArrayList<>();
        cartService.getItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setPerfume(cartItem.getPerfume());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPerfume().getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        });
        order.setItems(orderItems);

        orderRepository.save(order);

        Order savedOrder = orderRepository.save(order);

        cartService.clearCart();

        emailService.sendOrderConfirmationEmail(currentUser, savedOrder);
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono zamówienia o ID: " + orderId));

        order.setStatus(newStatus);
    }
}