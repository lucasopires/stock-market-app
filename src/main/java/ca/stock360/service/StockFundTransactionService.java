package ca.stock360.service;

import ca.stock360.persistence.StockFundRepository;
import ca.stock360.persistence.StockFundTransactionRepository;
import ca.stock360.persistence.StockRepository;
import ca.stock360.persistence.domains.Stock;
import ca.stock360.persistence.domains.StockFund;
import ca.stock360.persistence.domains.StockFundTransaction;
import ca.stock360.service.wrapper.CsvRowWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class StockFundTransactionService {

    private final StockFundTransactionRepository stockFundTransactinorepository;
    private final StockFundRepository stockFundRepository;
    private final StockRepository stockRepository;

    public StockFundTransactionService(
            StockFundTransactionRepository stockFundTransactinorepository,
            StockFundRepository stockFundRepository, StockRepository stockRepository) {
        this.stockFundTransactinorepository = stockFundTransactinorepository;
        this.stockFundRepository = stockFundRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional(readOnly = true)
    public Page<StockFundTransaction> findAllPageble(Pageable pageable) {
        return stockFundTransactinorepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<StockFundTransaction> findAll() {
        return stockFundTransactinorepository.findAll();
    }

    @Transactional
    public void loadTransactionsFromCsvFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(getTradesCsvFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                CsvRowWrapper rowWrapper = new CsvRowWrapper(line);

                Stock stock = stockRepository.findOneByTicker(rowWrapper.getStockTicker());
                if (stock == null)
                    stock = stockRepository.save(new Stock(rowWrapper.getStockTicker()));

                StockFund stockFund = stockFundRepository.findOneByTicker(rowWrapper.getFundTicker());
                if (stockFund == null)
                    stockFund = stockFundRepository.save(
                            new StockFund(
                                    rowWrapper.getFundTicker(),
                                    rowWrapper.getFundTicker()));


                StockFundTransaction fundTransaction = new StockFundTransaction();
                fundTransaction.setStock(stock);
                fundTransaction.setFund(stockFund);
                fundTransaction.setFundWeight(rowWrapper.getTransactionFundWeight());

                fundTransaction.setDate(rowWrapper.getTransactionDate());
                fundTransaction.setDirection(rowWrapper.getTransactionDirection());
                fundTransaction.setShares(rowWrapper.getNumberOfShares());
                fundTransaction.setCreatedAt(Instant.now());

                stockFundTransactinorepository.save(fundTransaction);
            }
        }
    }

    private File getTradesCsvFile() {
        String resourceName = "trades_test.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
    }
}
