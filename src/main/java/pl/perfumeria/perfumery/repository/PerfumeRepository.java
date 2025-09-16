package pl.perfumeria.perfumery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.perfumeria.perfumery.domain.Perfume;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {



}