package ca.stock360.persistence;

import ca.stock360.persistence.domains.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findOneByTicker(String ticker);

}
