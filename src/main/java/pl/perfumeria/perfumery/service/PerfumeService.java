package pl.perfumeria.perfumery.service;

import pl.perfumeria.perfumery.dto.PerfumeDto;

import java.util.Optional;

public interface PerfumeService {
    void addPerfume(PerfumeDto perfumeDto);
    Optional<PerfumeDto> findPerfumeById(Long id);
    void updatePerfume(PerfumeDto perfumeDto);
    void deletePerfume(Long id);
}