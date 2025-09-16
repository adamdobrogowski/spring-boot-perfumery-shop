package pl.perfumeria.perfumery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perfumeria.perfumery.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}