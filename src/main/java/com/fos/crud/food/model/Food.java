package com.fos.crud.food.model;

import jdk.jfr.DataAmount;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Food {
    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto increment
    private Long id;
    private String name;
    private BigDecimal salePrice;
    private BigDecimal costPrice;

    public Food() {
    }

    public Food(String name, BigDecimal salePrice, BigDecimal costPrice) {
        this.name = name;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salePrice=" + salePrice +
                ", costPrice=" + costPrice +
                '}';
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

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
}
