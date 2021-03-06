package ca.stock360.persistence;

import ca.stock360.persistence.domains.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

}
