package ru.geekbrains.myMarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.myMarket.dto.ProductDto;
import ru.geekbrains.myMarket.entities.Category;
import ru.geekbrains.myMarket.entities.Product;
import ru.geekbrains.myMarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.myMarket.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Optional<Product> findById(Long id){

        return productRepository.findById(id);
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
    public void updateProductFromDto(ProductDto productDto){
        Product product = findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + productDto.getId() + " не найден!"));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByName(productDto
                        .getCategoryName())
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием = " +
                        productDto.getCategoryName() + " не найдена"));
        product.setCategory(category);
    }
}
