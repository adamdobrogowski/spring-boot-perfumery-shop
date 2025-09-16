package pl.perfumeria.perfumery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.perfumeria.perfumery.domain.*;
import pl.perfumeria.perfumery.repository.BrandRepository;
import pl.perfumeria.perfumery.repository.CategoryRepository;
import pl.perfumeria.perfumery.repository.PerfumeRepository;
import pl.perfumeria.perfumery.repository.UserRepository;
import java.util.Set;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final PerfumeRepository perfumeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(BrandRepository brandRepository, CategoryRepository categoryRepository, PerfumeRepository perfumeRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.perfumeRepository = perfumeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (perfumeRepository.count() == 0) {

            Brand chanel = new Brand("Chanel", "Francja");
            Brand dior = new Brand("Dior", "Francja");
            Brand GiorgioArmani = new Brand("Giorgio Armani", "Włochy");
            Brand Versace = new Brand("Versace", "Włochy");
            brandRepository.saveAll(List.of(chanel, dior, GiorgioArmani, Versace));

            Category damskie = new Category("Damskie", "Zapachy dla kobiet");
            Category meskie = new Category("Męskie", "Zapachy dla mężczyzn");
            Category unisex = new Category("Unisex", "Zapachy uniwersalne");
            categoryRepository.saveAll(List.of(damskie, meskie, unisex));

            Perfume no5 = new Perfume();
            no5.setName("No. 5");
            no5.setBrand(chanel);
            no5.setCategory(damskie);
            no5.setPrice(new BigDecimal("450.00"));
            no5.setCapacity(500);
            no5.setDescription("Klasyczny, aldehydowy zapach dla kobiet.");
            no5.setConcentration(PerfumeConcentration.EAU_DE_PARFUM);
            no5.setImageUrl("/images/chanel-no5-edp.jpg");

            Perfume sauvage = new Perfume();
            sauvage.setName("Sauvage");
            sauvage.setBrand(dior);
            sauvage.setCategory(meskie);
            sauvage.setPrice(new BigDecimal("380.00"));
            sauvage.setCapacity(100);
            sauvage.setDescription("Świeży, surowy i szlachetny zapach dla mężczyzn.");
            sauvage.setConcentration(PerfumeConcentration.EAU_DE_TOILETTE);
            sauvage.setImageUrl("/images/dior-sauvage-edt.jpg");

            Perfume sauvageParfum = new Perfume();
            sauvageParfum.setName("Sauvage");
            sauvageParfum.setBrand(dior);
            sauvageParfum.setCategory(meskie);
            sauvageParfum.setPrice(new BigDecimal("500.00"));
            sauvageParfum.setCapacity(100);
            sauvageParfum.setDescription("Świeży, bogaty i elegancki zapach dla mężczyzn.");
            sauvageParfum.setConcentration(PerfumeConcentration.PARFUM);
            sauvageParfum.setImageUrl("/images/dior-sauvage-parfum.jpg");

            Perfume adgCologne = new Perfume();
            adgCologne.setName("Acqua Di Gio");
            adgCologne.setBrand(GiorgioArmani);
            adgCologne.setCategory(unisex);
            adgCologne.setPrice(new BigDecimal("550.00"));
            adgCologne.setCapacity(200);
            adgCologne.setDescription("Świeży, morski, uniwersalny zapach.");
            adgCologne.setConcentration(PerfumeConcentration.EAU_DE_COLOGNE);
            adgCologne.setImageUrl("/images/armani-adg-cologne.jpg");

            Perfume erosEdt = new Perfume();
            erosEdt.setName("Eros");
            erosEdt.setBrand(Versace);
            erosEdt.setCategory(meskie);
            erosEdt.setPrice(new BigDecimal("250.00"));
            erosEdt.setCapacity(50);
            erosEdt.setDescription("Słodki, cytrusowy zapach dla młodych mężczyzn");
            erosEdt.setConcentration(PerfumeConcentration.EAU_DE_TOILETTE);
            erosEdt.setImageUrl("/images/versace-eros-edt.jpg");

            Perfume adgParfum = new Perfume();
            adgParfum.setName("Acqua Di Gio");
            adgParfum.setBrand(GiorgioArmani);
            adgParfum.setCategory(meskie);
            adgParfum.setPrice(new BigDecimal("499.00"));
            adgParfum.setCapacity(75);
            adgParfum.setDescription("Elegancki, morski zapach.");
            adgParfum.setConcentration(PerfumeConcentration.PARFUM);
            adgParfum.setImageUrl("/images/armani-adg-parfum.jpg");

            perfumeRepository.saveAll(List.of(no5, sauvage, erosEdt, adgCologne, adgParfum, sauvageParfum));

            System.out.println(">>> Baza danych została zainicjalizowana przykładowymi danymi.");
        }

        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("Perfumery");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));
            userRepository.save(admin);
            System.out.println(">>> Stworzono konto administratora.");

            User uzytkownik = new User();
            uzytkownik.setFirstName("abc");
            uzytkownik.setLastName("xyz");
            uzytkownik.setEmail("user@gmail.com");
            uzytkownik.setPassword(passwordEncoder.encode("haslo123"));
            uzytkownik.setRoles(Set.of("ROLE_USER"));
            userRepository.save(uzytkownik);
            System.out.println(">>> Stworzono konto użytkownika.");
        }

    }
}