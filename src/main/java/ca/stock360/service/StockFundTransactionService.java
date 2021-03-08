package ca.stock360.service;

import ca.stock360.persistence.StockFundTransactionRepository;
import ca.stock360.persistence.domains.Stock;
import ca.stock360.persistence.domains.StockFund;
import ca.stock360.persistence.domains.StockFundTransaction;
import ca.stock360.persistence.domains.StockTransactionDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StockFundTransactionService {

    private final StockFundTransactionRepository repository;

    public StockFundTransactionService(StockFundTransactionRepository repository) {
        this.repository = repository;
    }

    public static LocalDate converCsvDateToInstant(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yy");
        return LocalDate.parse(date, formatter);
    }

    @Transactional(readOnly = true)
    public Page<StockFundTransaction> findAllPageble(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<StockFundTransaction> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void loadTransactionsFromCsvFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(getTradesCsvFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                Stock stock = new Stock();
                stock.setTicker(values[0]);

                StockFund stockFund = new StockFund();
                stockFund.setTicker(values[5]);
                stockFund.setName(values[5]);

                StockFundTransaction fundTransaction = new StockFundTransaction();
                fundTransaction.setStock(stock);
                fundTransaction.setFund(stockFund);
                fundTransaction.setFundWeight(Double.valueOf(values[4]));
                fundTransaction.setDate(converCsvDateToInstant(values[1]));
                fundTransaction.setDirection(StockTransactionDirection.valueOf(values[2]));
                fundTransaction.setShares(Double.valueOf(values[3]));
                fundTransaction.setCreatedAt(Instant.now());

                repository.save(fundTransaction);
            }
        }
    }

    private File getTradesCsvFile() {
        String resourceName = "trades_test.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        return file;
    }
}
