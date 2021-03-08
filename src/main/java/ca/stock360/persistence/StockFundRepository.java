package ca.stock360.persistence;

import ca.stock360.persistence.domains.StockFund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockFundRepository extends JpaRepository<StockFund, Long> {

}
