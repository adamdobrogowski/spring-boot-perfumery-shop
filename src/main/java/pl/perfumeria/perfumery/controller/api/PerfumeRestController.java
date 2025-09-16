package pl.perfumeria.perfumery.controller.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.perfumeria.perfumery.domain.Perfume;
import pl.perfumeria.perfumery.repository.PerfumeRepository;
import pl.perfumeria.perfumery.service.PerfumeService;

import java.util.List;

@RestController
@RequestMapping("/api/perfumes")
public class PerfumeRestController {

    private final PerfumeRepository perfumeRepository;

    public PerfumeRestController(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @GetMapping
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

}
