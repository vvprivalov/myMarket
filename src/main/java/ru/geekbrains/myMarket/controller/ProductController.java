package ru.geekbrains.myMarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.myMarket.model.Cart;
import ru.geekbrains.myMarket.model.Product;
import ru.geekbrains.myMarket.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final Cart cartProduct = new Cart();

    @GetMapping("/products")
    public Page<Product> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    @DeleteMapping("/products/{id}")
    public void delProduct(@PathVariable("id") Long id) {

        productService.delProduct(id);
    }

    @GetMapping("/products/{id}")
    public Product prepareProduct(@PathVariable Long id) {

        return productService.findById(id);
    }

    @GetMapping("/products/cart-content")
    public List<Product> getCart() {
        return cartProduct.getLstCart();
    }

    @PostMapping("/products/add-cart")
    public List<Product> addProductToCard(@RequestBody Product product) {

        return cartProduct.addProduct(product);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product product) {

        productService.updateProduct(product);
    }
}

