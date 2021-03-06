package ca.stock360.persistence.domains;

public enum StockTransactionDirection {
    BUY("B"), SELL("S");

    private final String code;

    StockTransactionDirection(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
