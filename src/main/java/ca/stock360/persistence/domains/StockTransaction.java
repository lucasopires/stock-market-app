package ca.stock360.persistence.domains;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "stock_fund_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StockTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    private StockFund fund;

    @NotNull
    @Column(name = "fund_weight")
    private Double fundWeight;

    @NotNull
    @Column(name = "shares")
    private Double shares;

    @NotNull
    @Column(name = "direction", nullable = false)
    private StockTransactionDirection direction;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockFund getFund() {
        return fund;
    }

    public void setFund(StockFund fund) {
        this.fund = fund;
    }

    public Double getFundWeight() {
        return fundWeight;
    }

    public void setFundWeight(Double fundWeight) {
        this.fundWeight = fundWeight;
    }

    public Double getShares() {
        return shares;
    }

    public void setShares(Double shares) {
        this.shares = shares;
    }

    public StockTransactionDirection getDirection() {
        return direction;
    }

    public void setDirection(StockTransactionDirection direction) {
        this.direction = direction;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockTransaction that = (StockTransaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "StockTransaction{" +
                "id=" + id +
                ", fund=" + fund +
                ", fundWeight=" + fundWeight +
                ", shares=" + shares +
                ", direction=" + direction +
                ", date=" + date +
                ", createdAt=" + createdAt +
                '}';
    }
}
