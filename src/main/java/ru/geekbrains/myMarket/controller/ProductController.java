package ru.geekbrains.myMarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.myMarket.model.Product;
import ru.geekbrains.myMarket.service.ProductService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public Page<Product> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10);
    }

//    @GetMapping("/product/{id}")
//    public Product findById(@PathVariable Long id) {
//        return productService.findById(id);
//    }

    @DeleteMapping(value = "/products/{id}")
    public void delProduct(@PathVariable("id") Long id) {

        productService.delProduct(id);
    }
}

