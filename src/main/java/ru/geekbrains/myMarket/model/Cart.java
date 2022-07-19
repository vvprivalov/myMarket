package ru.geekbrains.myMarket.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private final List<Product> lstCart;

    public Cart() {
        this.lstCart = new ArrayList<>();
    }

    public List<Product> getLstCart() {
        return lstCart;
    }

    public List<Product> addProduct(Product product) {
        lstCart.add(product);
        return lstCart;
    }
}
