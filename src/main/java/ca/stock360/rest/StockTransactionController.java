package ca.stock360.rest;

import ca.stock360.rest.resource.StockTransacionDot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockTransactionController {

    @GetMapping("/")
    public StockTransacionDot get() {
        return null;
    }

}
