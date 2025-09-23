package pl.perfumeria.perfumery.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.perfumeria.perfumery.domain.Perfume;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
    }

    @Test
    void shouldAddOneItemToCart() {
        Perfume perfume = new Perfume();
        perfume.setId(1L);
        perfume.setName("Test Perfume");
        perfume.setPrice(new BigDecimal("100.00"));

        cartService.addProduct(perfume);

        assertEquals(1, cartService.getItems().size(), "Koszyk powinien zawierać 1 element.");
        assertEquals(new BigDecimal("100.00"), cartService.getTotalPrice(), "Suma w koszyku powinna wynosić 100.00.");
    }

    @Test
    void shouldAddSameProductTwiceAndIncreaseQuantity() {
        Perfume perfume = new Perfume();
        perfume.setId(1L);
        perfume.setName("Test Perfume");
        perfume.setPrice(new BigDecimal("100.00"));

        cartService.addProduct(perfume);
        cartService.addProduct(perfume);

        assertEquals(1, cartService.getItems().size(), "Koszyk wciąż powinien zawierać 1 pozycję.");
        assertEquals(2, cartService.getItems().get(0).getQuantity(), "Ilość dla tej pozycji powinna wynosić 2.");
        assertEquals(new BigDecimal("200.00"), cartService.getTotalPrice(), "Suma w koszyku powinna wynosić 200.00.");
    }
}