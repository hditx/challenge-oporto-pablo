package com.listening.listening.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "special_prices", schema = "prueba")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SpecialPrices {
    private UUID id;
    private Listings listing_id;
    private Date date;
    private Double price;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id")
    public Listings getListing_id() {
        return listing_id;
    }

    public void setListing_id(Listings listing_id) {
        this.listing_id = listing_id;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialPrices that = (SpecialPrices) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, price);
    }
}
