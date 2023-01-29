package ru.geekbrains.myMarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.myMarket.dto.CategoryDto;
import ru.geekbrains.myMarket.dto.ProductDto;
import ru.geekbrains.myMarket.entities.Category;
import ru.geekbrains.myMarket.entities.Product;
import ru.geekbrains.myMarket.exceptions.DataValidationException;
import ru.geekbrains.myMarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.myMarket.services.CategoryService;
import ru.geekbrains.myMarket.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10).map(ProductDto::new);
    }

    @GetMapping("/products/categories")
    public List<CategoryDto> findAllCategories() {
        return categoryService.findAll().stream().map(CategoryDto::new).toList();
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found")));
    }


    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
            .getAllErrors()
            .stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList()));
        }
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByName(productDto
                .getCategoryName())
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием = " +
                        productDto.getCategoryName() + " не найдена"));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody ProductDto productDto) {

        productService.updateProductFromDto(productDto);
    }
}
