package ru.geekbrains.myMarket.dto;

import lombok.Data;
import ru.geekbrains.myMarket.entities.CartItem;
import ru.geekbrains.myMarket.entities.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal sum;

    public Cart() {
        items = new ArrayList<>();
    }

    public void add(Product product) {
        if (add(product.getId())) {
            return;
        }
        items.add(new CartItem(product));
        recalculate();
    }
    public boolean add(Long id) {
        for (CartItem c : items) {
            if (c.getProductId().equals(id)) {
                c.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void decrement(Long productId) {
        Iterator<CartItem> iter = items.iterator();
        while (iter.hasNext()) {
            CartItem c = iter.next();
            if (c.getProductId().equals(productId)) {
                c.changeQuantity(-1);
                if (c.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
        recalculate();
    }

    public void clear() {
        items.clear();
        sum = BigDecimal.ZERO;
    }

    private void recalculate() {
        sum = BigDecimal.ZERO;
        for (CartItem item : items) {
            sum = sum.add(item.getSum());
        }
    }

    public void merge(Cart another) {
        for (CartItem anotherItem : another.items) {
            boolean merged = false;
            for (CartItem myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}

