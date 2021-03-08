package ca.stock360.service;

import ca.stock360.MainApp;
import ca.stock360.service.wrapper.CsvRowWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = MainApp.class)
public class StockFundTransactionServiceIT {

    @Autowired
    public StockFundTransactionService service;

//    @Autowired
//    private StockFundTransactionRepository repository;
//
//    @BeforeEach
//    public void setup() {
//        //service = new StockFundTransactionService(repository);
//    }

    @Test
    void loadTransactionsFromCsvFileHappyPath() throws IOException {
        service.loadTransactionsFromCsvFile();
        service.loadTransactionsFromCsvFile();
    }

    @Test
    void convertDateConversion() {
        LocalDate dateToCompare = LocalDate.of(2021, 3, 21);
        LocalDate date = CsvRowWrapper.converCsvDateToInstant("21-Mar-21");
        assertTrue(date.isEqual(dateToCompare));
    }

}
