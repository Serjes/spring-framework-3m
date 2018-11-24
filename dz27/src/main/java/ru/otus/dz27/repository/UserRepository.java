package ru.otus.dz27.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dz27.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);
}
