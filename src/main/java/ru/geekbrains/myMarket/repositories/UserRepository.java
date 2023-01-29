package ru.geekbrains.myMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.myMarket.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Override
    <S extends User> S save(S entity);
}
