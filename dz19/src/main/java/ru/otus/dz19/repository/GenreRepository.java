package ru.otus.dz19.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dz19.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Genre findByName(String name);

}
