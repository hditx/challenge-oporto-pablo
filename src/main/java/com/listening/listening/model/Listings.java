package com.listening.listening.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "listings", schema = "prueba")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Listings {
    private UUID id;
    private Users owner_id;
    private String name;
    private String slug;
    private String description;
    private Integer adults;
    private Integer children;
    private Boolean is_pets_allowed;
    private Double base_price;
    private Double cleaning_fee;
    private String image_url;
    private Double weekly_discount;
    private Double monthly_discount;
    private Set<SpecialPrices> special_prices;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    public Users getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Users owner_id) {
        this.owner_id = owner_id;
    }

    @Basic
    @Column(name = "name", table = "listings")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "slug", table = "listings")
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Basic
    @Column(name = "description", table = "listings")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "adults", table = "listings")
    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    @Basic
    @Column(name = "children", table = "listings")
    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    @Basic
    @Column(name = "is_pets_allowed", table = "listings")
    public Boolean getIs_pets_allowed() {
        return is_pets_allowed;
    }

    public void setIs_pets_allowed(Boolean is_pets_allowed) {
        this.is_pets_allowed = is_pets_allowed;
    }

    @Basic
    @Column(name = "base_price", table = "listings")
    public Double getBase_price() {
        return base_price;
    }

    public void setBase_price(Double base_price) {
        this.base_price = base_price;
    }

    @Basic
    @Column(name = "cleaning_fee", table = "listings")
    public Double getCleaning_fee() {
        return cleaning_fee;
    }

    public void setCleaning_fee(Double cleaning_fee) {
        this.cleaning_fee = cleaning_fee;
    }

    @Basic
    @Column(name = "image_url", table = "listings")
    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Basic
    @Column(name = "weekly_discount", table = "listings")
    public Double getWeekly_discount() {
        return weekly_discount;
    }

    public void setWeekly_discount(Double weekly_discount) {
        this.weekly_discount = weekly_discount;
    }

    @Basic
    @Column(name = "monthly_discount", table = "listings")
    public Double getMonthly_discount() {
        return monthly_discount;
    }

    public void setMonthly_discount(Double monthly_discount) {
        this.monthly_discount = monthly_discount;
    }

    @OneToMany(mappedBy = "listing_id", fetch = FetchType.LAZY)
    public Set<SpecialPrices> getSpecial_prices() {
        return special_prices;
    }

    public void setSpecial_prices(Set<SpecialPrices> special_prices) {
        this.special_prices = special_prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listings listings = (Listings) o;
        return Objects.equals(id, listings.id) &&
                Objects.equals(name, listings.name) &&
                Objects.equals(slug, listings.slug) &&
                Objects.equals(description, listings.description) &&
                Objects.equals(adults, listings.adults) &&
                Objects.equals(children, listings.children) &&
                Objects.equals(is_pets_allowed, listings.is_pets_allowed) &&
                Objects.equals(base_price, listings.base_price) &&
                Objects.equals(cleaning_fee, listings.cleaning_fee) &&
                Objects.equals(image_url, listings.image_url) &&
                Objects.equals(weekly_discount, listings.weekly_discount) &&
                Objects.equals(monthly_discount, listings.monthly_discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slug, description, adults, children, is_pets_allowed, base_price, cleaning_fee, image_url, weekly_discount, monthly_discount);
    }
}
