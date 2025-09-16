package pl.perfumeria.perfumery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perfumeria.perfumery.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {



}