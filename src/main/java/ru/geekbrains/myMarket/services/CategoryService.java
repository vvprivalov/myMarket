package ru.geekbrains.myMarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.myMarket.dto.CategoryDto;
import ru.geekbrains.myMarket.entities.Category;
import ru.geekbrains.myMarket.entities.Product;
import ru.geekbrains.myMarket.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByName(String name) {

        return categoryRepository.findByName(name);
    }

    public Optional<Category> findById(Long id) {

        return categoryRepository.findById(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
