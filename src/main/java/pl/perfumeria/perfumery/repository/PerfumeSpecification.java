package pl.perfumeria.perfumery.repository;

import org.springframework.data.jpa.domain.Specification;
import pl.perfumeria.perfumery.domain.Perfume;

public class PerfumeSpecification {

    public static Specification<Perfume> hasBrand(Long brandId) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("brand").get("id"), brandId));
    }

    public static Specification<Perfume> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Perfume> nameContains(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
    }

}
