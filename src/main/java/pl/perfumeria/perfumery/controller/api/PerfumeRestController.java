package pl.perfumeria.perfumery.controller.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.perfumeria.perfumery.domain.Perfume;
import pl.perfumeria.perfumery.repository.PerfumeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/perfumes")
public class PerfumeRestController {

    private final PerfumeRepository perfumeRepository;

    public PerfumeRestController(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Perfume>> getAllPerfumes() {
        List<Perfume> result = perfumeRepository.findAll();
        if(result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

}
