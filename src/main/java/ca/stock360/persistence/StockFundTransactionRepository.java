package ca.stock360.persistence;

import ca.stock360.persistence.domains.StockFundTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockFundTransactionRepository extends JpaRepository<StockFundTransaction, Long> {

}
