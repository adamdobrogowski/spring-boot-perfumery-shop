package pl.perfumeria.perfumery.service;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.perfumeria.perfumery.domain.Perfume;
import pl.perfumeria.perfumery.dto.CartItemDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    private final List<CartItemDto> items = new ArrayList<>();

    public void addProduct(Perfume perfume) {
        Optional<CartItemDto> existingItem = findItembyPerfumeId(perfume.getId());

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + 1);
        } else {
            items.add(new CartItemDto(perfume, 1));
        }
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return items.stream().map(CartItemDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Optional<CartItemDto> findItembyPerfumeId(Long perfumeId) {
        return items.stream().filter(item -> item.getPerfume().getId().equals(perfumeId)).findFirst();
    }

    public void clearCart() {
        items.clear();
    }

}
