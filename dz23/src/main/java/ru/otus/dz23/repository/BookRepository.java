package ru.otus.dz23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.dz23.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

    List<Book> findAll();

//    Optional<Book> findById(Integer id);
//
//    void deleteById(Integer id);
//
//    Book save(Book book);

}
