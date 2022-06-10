package ru.geekbrains.myMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.myMarket.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findProductByPriceBetween(int min, int max);

    List<Product> findProductByPriceAfter(int min);

    List<Product> findProductByPriceBefore(int max);

    void deleteById(Long id);

    <S extends Product> S save(S product);
}
