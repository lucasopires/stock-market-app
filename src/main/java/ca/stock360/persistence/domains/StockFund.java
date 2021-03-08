package ca.stock360.persistence.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "stock_fund")
public class StockFund implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ticker")
    private String ticker;

    @NotNull
    @Column(name = "name")
    private String name;

    public StockFund() {
    }

    public StockFund(@NotNull String ticker, @NotNull String name) {
        this.ticker = ticker;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockFund stockFund = (StockFund) o;
        return ticker.equals(stockFund.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker);
    }

    @Override
    public String toString() {
        return "StockFund{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
