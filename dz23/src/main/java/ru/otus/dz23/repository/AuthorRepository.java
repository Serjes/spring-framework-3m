package ru.otus.dz23.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.dz23.domain.Author;

import java.util.List;


public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {

    List<Author> findAll();

}
