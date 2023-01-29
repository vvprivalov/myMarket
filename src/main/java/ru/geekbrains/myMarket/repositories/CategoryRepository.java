package ru.geekbrains.myMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.myMarket.entities.Category;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Optional<Category> findById(Long id);
}
