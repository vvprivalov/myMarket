package ru.geekbrains.myMarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.myMarket.model.Product;
import ru.geekbrains.myMarket.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            return productRepository.findById(id).get();
        }
        return new Product();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void delProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByMinPrice(int min) {
        return productRepository.findProductByPriceAfter(min);
    }

    public List<Product> findByMaxPrice(int max) {
        return productRepository.findProductByPriceBefore(max);
    }

    public List<Product> findBetweenMinMax(int min, int max) {
        return productRepository.findProductByPriceBetween(min, max);
    }
}
