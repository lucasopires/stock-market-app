package ca.stock360.persistence;

import ca.stock360.persistence.domains.StockFund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockFundRepository extends JpaRepository<StockFund, Long> {

    StockFund findOneByTicker(String ticker);

}
