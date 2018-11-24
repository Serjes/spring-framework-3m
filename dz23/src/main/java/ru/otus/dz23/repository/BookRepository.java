package ru.otus.dz23.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.dz23.domain.Book;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

    List<Book> findAll();

}
