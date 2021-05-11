package de.hoomb.stockmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Models the Product object
 *
 * @author Hooman Behmanesh
 */
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    private String name;
    private long stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonManagedReference
    private Set<Reservation> reservations;

    @Version
    @JsonIgnore
    @NotNull
    private Integer version;

    @CreatedDate
    @JsonIgnore
    @NotNull
    private Date createdDate;

    @LastModifiedDate
    @JsonIgnore
    @NotNull
    private Date lastModified;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Product(final String name, final long stock, final Set<Reservation> reservations, final Integer version) {
        this.name = name;
        this.stock = stock;
        this.reservations = reservations;
        this.version = version;
    }
}
