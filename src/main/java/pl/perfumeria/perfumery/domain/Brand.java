package pl.perfumeria.perfumery.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "brand-seq")
    private Long id;

    private String name;

    private String countryOfOrigin;

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private List<Perfume> perfumes;

    public Brand(String name, String countryOfOrigin) {
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
    }
}