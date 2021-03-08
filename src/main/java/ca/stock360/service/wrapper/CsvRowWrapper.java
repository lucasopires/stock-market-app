package ca.stock360.service.wrapper;

import ca.stock360.persistence.domains.StockTransactionDirection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CsvRowWrapper {

    private final String stockTicker;
    private final String fundTicker;
    private final Double transactionFundWeight;
    private final LocalDate transactionDate;
    private final StockTransactionDirection transactionDirection;
    private final Double numberOfShares;

    public CsvRowWrapper(String csvRow) {
        String[] values = csvRow.split(",");
        this.stockTicker = values[0];
        this.fundTicker = values[5];
        this.transactionFundWeight = Optional.ofNullable(Double.valueOf(values[4])).orElse(0d);
        this.transactionDate = converCsvDateToInstant(values[1]);
        this.transactionDirection = StockTransactionDirection.valueOf(values[2]);
        this.numberOfShares = Optional.ofNullable(Double.valueOf(values[3])).orElse(0d);
    }

    public static LocalDate converCsvDateToInstant(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yy");
        return LocalDate.parse(date, formatter);
    }

    public String getStockTicker() {
        return stockTicker;
    }

    public String getFundTicker() {
        return fundTicker;
    }

    public Double getTransactionFundWeight() {
        return transactionFundWeight;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public StockTransactionDirection getTransactionDirection() {
        return transactionDirection;
    }

    public Double getNumberOfShares() {
        return numberOfShares;
    }
}
