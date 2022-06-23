package ru.geekbrains.myMarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Product> findAll(int pageIndex, int pageSize) {

        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void delProduct(Long id) {

        productRepository.deleteById(id);
    }
}
