package com.listening.listening.dto;


import java.util.Date;

public class SpecialPricesDto {
    private Date date;
    private Double price;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
