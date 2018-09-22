package ru.otus.dz19.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dz19.domain.Book;
import ru.otus.dz19.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByBook(Book book);

    List<Comment> findAll();
}
