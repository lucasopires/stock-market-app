package ca.stock360.rest;

import ca.stock360.persistence.StockFundRepository;
import ca.stock360.persistence.domains.StockFund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockFundController {

    private final Logger log = LoggerFactory.getLogger(StockFundTransactionController.class);
    private final StockFundRepository repository;

    public StockFundController(StockFundRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/v1/funds")
    public List<StockFund> getAll() {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get all stock transactions");
        }
        return repository.findAll();
    }

    @PutMapping("/v1/funds/{id}")
    public StockFund replaceEmployee(@RequestBody StockFund newStockFund, @PathVariable Long id) {

        return repository.findById(id)
                .map(fund -> {
                    fund.setName(newStockFund.getName());
                    fund.setTicker(newStockFund.getTicker());
                    return repository.save(fund);
                })
                .orElseGet(() -> {
                    newStockFund.setId(id);
                    return repository.save(newStockFund);
                });
    }
}
