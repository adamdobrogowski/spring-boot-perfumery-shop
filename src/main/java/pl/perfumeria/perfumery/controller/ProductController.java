package pl.perfumeria.perfumery.controller;


import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.perfumeria.perfumery.repository.PerfumeRepository;

@Controller
public class ProductController {

    private final PerfumeRepository perfumeRepository;

    public ProductController(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("perfumes", perfumeRepository.findAll());
        return "product-list";
    }
}
