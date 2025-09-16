package pl.perfumeria.perfumery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perfumeria.perfumery.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}