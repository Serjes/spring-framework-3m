package ru.otus.dz23.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.dz23.domain.Author;
import ru.otus.dz23.domain.Book;

import java.util.List;


public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {

    List<Author> findAll();

//    Optional<Book> findById(Integer id);
//
//    void deleteById(Integer id);
//
//    Book save(Book book);

}
