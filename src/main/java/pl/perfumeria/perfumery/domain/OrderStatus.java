package pl.perfumeria.perfumery.domain;


public enum OrderStatus {
    NEW("Nowe"),
    PROCESSING("W trakcie realizacji"),
    SHIPPED("Wysłane"),
    DELIVERED("Dostarczone"),
    CANCELLED("Anulowane");

    private final String displayName;

    OrderStatus(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}