package ru.otus.dz21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dz21.domain.Book;
import ru.otus.dz21.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByBook(Book book);

    List<Comment> findAll();
}
