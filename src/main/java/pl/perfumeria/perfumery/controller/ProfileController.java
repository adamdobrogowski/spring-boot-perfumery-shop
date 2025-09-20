package pl.perfumeria.perfumery.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.perfumeria.perfumery.domain.Order;
import pl.perfumeria.perfumery.domain.User;
import pl.perfumeria.perfumery.repository.OrderRepository;
import pl.perfumeria.perfumery.repository.UserRepository;

import java.util.List;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public ProfileController(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/my-profile")
    public String showProfilePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        List<Order> orders = orderRepository.findByUser_Email(email);

        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "profile";
    }
}