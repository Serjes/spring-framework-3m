package ru.otus.dz23.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.dz23.domain.Book;

public interface MongoBookRepository extends MongoRepository<Book, String> {
}
