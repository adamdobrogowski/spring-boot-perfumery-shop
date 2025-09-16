package pl.perfumeria.perfumery.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.perfumeria.perfumery.domain.Brand;
import pl.perfumeria.perfumery.domain.Category;
import pl.perfumeria.perfumery.domain.Perfume;
import pl.perfumeria.perfumery.dto.PerfumeDto;
import pl.perfumeria.perfumery.repository.BrandRepository;
import pl.perfumeria.perfumery.repository.CategoryRepository;
import pl.perfumeria.perfumery.repository.PerfumeRepository;

import java.util.Optional;

@Service
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public PerfumeServiceImpl(PerfumeRepository perfumeRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.perfumeRepository = perfumeRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<PerfumeDto> findPerfumeById(Long id) {
        return perfumeRepository.findById(id).map(this::mapToDto);
    }

    private PerfumeDto mapToDto(Perfume perfume) {
        PerfumeDto dto = new PerfumeDto();
        dto.setId(perfume.getId());
        dto.setName(perfume.getName());
        dto.setDescription(perfume.getDescription());
        dto.setPrice(perfume.getPrice());
        dto.setCapacity(perfume.getCapacity());
        dto.setImageUrl(perfume.getImageUrl());
        dto.setConcentration(perfume.getConcentration());
        dto.setBrandId(perfume.getBrand().getId());
        dto.setCategoryId(perfume.getCategory().getId());
        return dto;
    }

    @Override
    public void addPerfume(PerfumeDto perfumeDto) {
        Perfume perfume = new Perfume();
        perfume.setName(perfumeDto.getName());
        perfume.setDescription(perfumeDto.getDescription());
        perfume.setPrice(perfumeDto.getPrice());
        perfume.setCapacity(perfumeDto.getCapacity());
        perfume.setImageUrl(perfumeDto.getImageUrl());
        perfume.setConcentration(perfumeDto.getConcentration());

        Brand brand = brandRepository.findById(perfumeDto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono marki o ID: " + perfumeDto.getBrandId()));
        perfume.setBrand(brand);

        Category category = categoryRepository.findById(perfumeDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o ID: " + perfumeDto.getCategoryId()));
        perfume.setCategory(category);

        perfumeRepository.save(perfume);
    }


    @Override
    @Transactional
    public void updatePerfume(PerfumeDto perfumeDto) {
        Perfume perfume = perfumeRepository.findById(perfumeDto.getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono produktu o ID: " + perfumeDto.getId()));

        perfume.setName(perfumeDto.getName());
        perfume.setDescription(perfumeDto.getDescription());
        perfume.setPrice(perfumeDto.getPrice());
        perfume.setCapacity(perfumeDto.getCapacity());
        perfume.setImageUrl(perfumeDto.getImageUrl());
        perfume.setConcentration(perfumeDto.getConcentration());

        Brand brand = brandRepository.findById(perfumeDto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono marki o ID: " + perfumeDto.getBrandId()));
        perfume.setBrand(brand);

        Category category = categoryRepository.findById(perfumeDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o ID: " + perfumeDto.getCategoryId()));
        perfume.setCategory(category);
        perfumeRepository.save(perfume);

    }

    @Override
    public void deletePerfume(Long id) {
        perfumeRepository.deleteById(id);
    }



}
