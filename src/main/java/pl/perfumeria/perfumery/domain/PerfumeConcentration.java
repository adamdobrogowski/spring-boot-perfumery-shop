package pl.perfumeria.perfumery.domain;

import lombok.Getter;

@Getter
public enum PerfumeConcentration {

    EAU_DE_COLOGNE("Woda kolońska (EDC)"),
    EAU_DE_TOILETTE("Woda toaletowa (EDT)"),
    EAU_DE_PARFUM("Woda perfumowana (EDP)"),
    PARFUM("Perfumy (PARFUM)");

    private final String displayName;

    PerfumeConcentration(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}