package pl.perfumeria.perfumery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perfumeria.perfumery.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}