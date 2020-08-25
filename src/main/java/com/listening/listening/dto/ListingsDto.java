package com.listening.listening.dto;

import com.listening.listening.model.SpecialPrices;
import com.listening.listening.model.Users;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ListingsDto {
    private UUID id;
    private UUID owner_id;
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
    private List<SpecialPricesDto> special_prices;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(UUID owner_id) {
        this.owner_id = owner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Boolean getIs_pets_allowed() {
        return is_pets_allowed;
    }

    public void setIs_pets_allowed(Boolean is_pets_allowed) {
        this.is_pets_allowed = is_pets_allowed;
    }

    public Double getBase_price() {
        return base_price;
    }

    public void setBase_price(Double base_price) {
        this.base_price = base_price;
    }

    public Double getCleaning_fee() {
        return cleaning_fee;
    }

    public void setCleaning_fee(Double cleaning_fee) {
        this.cleaning_fee = cleaning_fee;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Double getWeekly_discount() {
        return weekly_discount;
    }

    public void setWeekly_discount(Double weekly_discount) {
        this.weekly_discount = weekly_discount;
    }

    public Double getMonthly_discount() {
        return monthly_discount;
    }

    public void setMonthly_discount(Double monthly_discount) {
        this.monthly_discount = monthly_discount;
    }

    public List<SpecialPricesDto> getSpecial_prices() {
        return special_prices;
    }

    public void setSpecial_prices(List<SpecialPricesDto> special_prices) {
        this.special_prices = special_prices;
    }
}
