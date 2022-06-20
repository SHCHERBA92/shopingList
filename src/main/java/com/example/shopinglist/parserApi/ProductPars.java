package com.example.shopinglist.parserApi;

import java.math.BigDecimal;


public class ProductPars {
    String name;

    BigDecimal price;

    String img;

    public ProductPars(String name, BigDecimal price, String img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public ProductPars() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "ProductPars{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                '}';
    }
}


//https://cdn-img.perekrestok.ru/i/400x400-fit/xdelivery/files/c0/5f/7e321eb6c5dd996xdba36773184a.jpg