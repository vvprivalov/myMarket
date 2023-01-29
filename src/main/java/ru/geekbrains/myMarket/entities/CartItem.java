package ru.geekbrains.myMarket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal price;
    private BigDecimal sum;

    public CartItem(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getName();
        this.quantity = 1;
        this.price = product.getPrice();
        this.sum = price.multiply(BigDecimal.valueOf(quantity));
    }

    public void changeQuantity(int delta) {
        quantity += delta;
        sum = price.multiply(BigDecimal.valueOf(quantity));
    }
}
