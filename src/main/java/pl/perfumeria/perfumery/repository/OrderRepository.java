package pl.perfumeria.perfumery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perfumeria.perfumery.domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_Email(String email);

}