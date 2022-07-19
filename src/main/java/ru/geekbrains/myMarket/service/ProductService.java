package ru.geekbrains.myMarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.myMarket.model.Product;
import ru.geekbrains.myMarket.repository.ProductRepository;

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

        return productRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("id").ascending()));
    }

    public void delProduct(Long id) {

        productRepository.deleteById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Product product) {
        if (productRepository.findById(product.getId()).isPresent()) {
            Product prod = productRepository.findById(product.getId()).get();
            prod.setName(product.getName());
            prod.setPrice(product.getPrice());
        }
    }
}
