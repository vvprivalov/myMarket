package ru.geekbrains.myMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.myMarket.entities.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

   void deleteById(Long id);

    <S extends Product> S save(S product);


}
