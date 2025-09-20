package pl.perfumeria.perfumery.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.perfumeria.perfumery.domain.Perfume;
import pl.perfumeria.perfumery.repository.BrandRepository;
import pl.perfumeria.perfumery.repository.CategoryRepository;
import pl.perfumeria.perfumery.repository.PerfumeRepository;
import pl.perfumeria.perfumery.repository.PerfumeSpecification;

@Controller
public class ProductController {

    private final PerfumeRepository perfumeRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(PerfumeRepository perfumeRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.perfumeRepository = perfumeRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/products")
    public String listProducts(
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keyword,
            Model model,
            Pageable pageable) {

        Specification<Perfume> finalSpec = createSpecification(brandId, categoryId, keyword);

        Page<Perfume> perfumePage = perfumeRepository.findAll(finalSpec, pageable);

        model.addAttribute("perfumePage", perfumePage);
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("selectedBrandId", brandId);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("selectedKeyword", keyword);

        return "product-list";
    }

    private Specification<Perfume> createSpecification(Long brandId, Long categoryId, String keyword) {
        Specification<Perfume> specByBrand = brandId != null ? PerfumeSpecification.hasBrand(brandId) : null;
        Specification<Perfume> specByCategory = categoryId != null ? PerfumeSpecification.hasCategory(categoryId) : null;
        Specification<Perfume> specByKeyword = keyword != null && !keyword.isEmpty() ? PerfumeSpecification.nameContains(keyword) : null;

        return Specification.<Perfume>unrestricted().and(specByBrand).and(specByCategory).and(specByKeyword);
    }
}