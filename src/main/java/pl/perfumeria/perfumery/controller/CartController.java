package pl.perfumeria.perfumery.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.perfumeria.perfumery.domain.Perfume;
import pl.perfumeria.perfumery.repository.PerfumeRepository;
import pl.perfumeria.perfumery.service.CartService;

@Controller
public class CartController {

    private final CartService cartService;
    private final PerfumeRepository perfumeRepository;

    public CartController(CartService cartService, PerfumeRepository perfumeRepository) {
        this.cartService = cartService;
        this.perfumeRepository = perfumeRepository;
    }

    @PostMapping("/cart/add/{perfumeId}")
    public String addProductToCart(@PathVariable Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId).orElseThrow(() -> new IllegalArgumentException("Invalid perfume id: "+ perfumeId));
        cartService.addProduct(perfume);
        return "redirect:/products";
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart-view";
    }

}
