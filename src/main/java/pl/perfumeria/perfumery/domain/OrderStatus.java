package pl.perfumeria.perfumery.domain;

public enum OrderStatus {
    NEW("Nowe"),
    PROCESSING("W trakcie realizacji"),
    SHIPPED("Wysłane"),
    DELIVERED("Dostarczone"),
    CANCELLED("Anulowane");

    private final String displatName;

    OrderStatus(final String displatName) {
        this.displatName = displatName;
    }

    public String getDisplatName() {
        return displatName;
    }
}