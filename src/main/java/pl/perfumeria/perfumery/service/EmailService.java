package pl.perfumeria.perfumery.service;

import pl.perfumeria.perfumery.domain.Order;
import pl.perfumeria.perfumery.domain.User;

public interface EmailService {
    void sendOrderConfirmationEmail(User user, Order order);
}
