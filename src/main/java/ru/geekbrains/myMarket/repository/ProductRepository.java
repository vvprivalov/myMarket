package ru.geekbrains.myMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.myMarket.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

   void deleteById(Long id);

    <S extends Product> S save(S product);


}
