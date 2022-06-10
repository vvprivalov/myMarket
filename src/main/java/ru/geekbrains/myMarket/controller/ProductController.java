package ru.geekbrains.myMarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.myMarket.model.Product;
import ru.geekbrains.myMarket.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/productdel/{id}")
    public void delProduct(@PathVariable Long id) {
        productService.delProduct(id);
    }

    @GetMapping("/products/insert")
    public void insertNewProduct(@RequestParam String name, @RequestParam int price) {
        productService.createProduct(new Product(name, price));
    }

    @GetMapping("/filter/min")
    public List<Product> findProductByPriceMin(@RequestParam int min) {
        return productService.findByMinPrice(min);
    }

    @GetMapping("/filter/max")
    public List<Product> findProductByPriceMax(@RequestParam int max) {
        return productService.findByMaxPrice(max);
    }

    @GetMapping("/filter/between")
    public List<Product> findProductByPriceBetween(@RequestParam int min, @RequestParam int max) {
        return productService.findBetweenMinMax(min, max);
    }
}

