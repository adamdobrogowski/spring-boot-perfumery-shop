package pl.perfumeria.perfumery.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import pl.perfumeria.perfumery.domain.Perfume;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartItemDto {
    private Perfume perfume;
    private int quantity;

    public BigDecimal getPrice() {
        return perfume.getPrice().multiply(new BigDecimal(quantity));
    }
}
