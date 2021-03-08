package ca.stock360.rest;

import ca.stock360.persistence.domains.StockFundTransaction;
import ca.stock360.service.StockFundTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockFundTransactionController {

    private final Logger log = LoggerFactory.getLogger(StockFundTransactionController.class);
    private final StockFundTransactionService service;

    public StockFundTransactionController(StockFundTransactionService service) {
        this.service = service;
    }

//    @GetMapping("/v1/funds/transactions")
//    public ResponseEntity<List<StockFundTransaction>> getAll(Pageable pageable) {
//        if (log.isDebugEnabled()) {
//            log.debug("REST request to get all stock transactions");
//        }
//
//        Page<StockFundTransaction> page = service.findAll(pageable);
//        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
//    }

    @GetMapping("/v1/funds/transactions")
    public List<StockFundTransaction> getAll() {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get all stock transactions");
        }
        return service.findAll();
    }

}
