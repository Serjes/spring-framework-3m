package ru.otus.dz27.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dz27.domain.Book;
import ru.otus.dz27.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByBook(Book book);

    List<Comment> findAll();
}
