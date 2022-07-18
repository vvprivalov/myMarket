package ru.geekbrains.myMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.myMarket.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

 }
