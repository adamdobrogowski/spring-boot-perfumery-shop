package pl.perfumeria.perfumery.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "perfume-seq")
    private Long id;
    private String name;

    @Column(length = 1000)
    private String description;
    private BigDecimal price;
    private int capacity;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private PerfumeConcentration concentration;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
