package pl.perfumeria.perfumery.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.perfumeria.perfumery.domain.Brand;
import pl.perfumeria.perfumery.domain.PerfumeConcentration;
import pl.perfumeria.perfumery.dto.PerfumeDto;
import pl.perfumeria.perfumery.repository.BrandRepository;
import pl.perfumeria.perfumery.repository.CategoryRepository;
import pl.perfumeria.perfumery.repository.OrderRepository;
import pl.perfumeria.perfumery.repository.PerfumeRepository;
import pl.perfumeria.perfumery.service.PerfumeService;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PerfumeRepository perfumeRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final PerfumeService perfumeService;
    private final OrderRepository orderRepository;


    public AdminController(PerfumeRepository perfumeRepository, BrandRepository brandRepository, CategoryRepository categoryRepository, PerfumeService perfumeService, OrderRepository orderRepository) {
        this.perfumeRepository = perfumeRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.perfumeService = perfumeService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("perfumes", perfumeRepository.findAll());
        return "admin/product-list";
    }

    @GetMapping("/products/add")
    public String showAddPerfumeForm(Model model) {
        model.addAttribute("perfume", new PerfumeDto());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("concentrations", PerfumeConcentration.values());
        return "admin/product-form";
    }

    @PostMapping("/products/add")
    public String addPerfume(@Valid @ModelAttribute("perfume") PerfumeDto perfumeDto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("concentrations", PerfumeConcentration.values());
            return "admin/product-form";
        }
        perfumeService.addPerfume(perfumeDto);
        return "redirect:/admin/products";
    }

    @GetMapping("/brands")
    public String showBrands(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("newBrand", new Brand());
        return "admin/brands";
    }

    @PostMapping("/brands/add")
    public String addBrand(@ModelAttribute("newBrand") Brand brand) {
        brandRepository.save(brand);
        return "redirect:/admin/brands";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditPerfumeForm(@PathVariable("id") Long id, Model model) {
        Optional<PerfumeDto> perfumeDto = perfumeService.findPerfumeById(id);
        if (perfumeDto.isPresent()) {
            model.addAttribute("perfume", perfumeDto.get());
            model.addAttribute("brands", brandRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("concentrations", PerfumeConcentration.values());
            return "admin/product-form";
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/products/edit/{id}")
    public String updatePerfume(@PathVariable Long id, @Valid @ModelAttribute("perfume") PerfumeDto perfumeDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("concentrations", PerfumeConcentration.values());
            return "admin/product-form";
        }
        perfumeDto.setId(id);
        perfumeService.updatePerfume(perfumeDto);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/delete/{id}")
    public String deletePerfume(@PathVariable Long id) {
        perfumeService.deletePerfume(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/order-list";
    }

}