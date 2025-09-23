package pl.perfumeria.perfumery.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.perfumeria.perfumery.dto.UserRegistrationDto;
import pl.perfumeria.perfumery.repository.UserRepository;
import pl.perfumeria.perfumery.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserRegistrationDto());
        return "registration-form";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("userDto") UserRegistrationDto userDto,
                                      BindingResult bindingResult) {

        if (userRepository.existsByEmail(userDto.getEmail())) {
            bindingResult.rejectValue("email", "email.exists", "Użytkownik z tym adresem e-mail już istnieje.");
        }

        if (bindingResult.hasErrors()) {
            return "registration-form";
        }

        userService.registerUser(userDto);
        return "redirect:/login?register_success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}