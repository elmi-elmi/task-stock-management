package de.hoomb.stockmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    private Date pointOfTime;

    public Reservation() {
    }

    public Reservation(final long amount, final Product product, final Date pointOfTime) {
        this.amount = amount;
        this.product = product;
        this.pointOfTime = pointOfTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(final long amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public Date getPointOfTime() {
        return pointOfTime;
    }

    public void setPointOfTime(final Date pointOfTime) {
        this.pointOfTime = pointOfTime;
    }
}
