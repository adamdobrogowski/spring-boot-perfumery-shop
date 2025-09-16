package pl.perfumeria.perfumery.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import pl.perfumeria.perfumery.domain.PerfumeConcentration;

import java.math.BigDecimal;

@Getter
@Setter
public class PerfumeDto {

    private Long id;

    @NotEmpty(message = "Nazwa nie może być pusta")
    private String name;

    @NotEmpty
    @Size(min = 10, message = "Opis musi mieć co najmniej 10 znaków")
    private String description;

    @NotNull(message = "Cena nie może być pusta")
    @DecimalMin(value = "0.01", message = "Cena musi być większa od 0")
    private BigDecimal price;

    @NotNull(message = "Pojemność nie może być pusta.")
    @Min(value = 1, message = "Pojemność musi być większa niż 0.")
    private int capacity;

    private String imageUrl;

    @NotNull(message = "Należy wybrać koncentrację.")
    private PerfumeConcentration concentration;

    @NotNull(message = "Należy wybrać markę.")
    private Long brandId;

    @NotNull(message = "Należy wybrać kategorię.")
    private Long categoryId;

}
