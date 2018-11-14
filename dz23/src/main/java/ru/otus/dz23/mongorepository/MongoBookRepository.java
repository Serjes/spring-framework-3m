package ru.otus.dz23.mongorepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.dz23.domain.MongoBook;

import java.util.List;

public interface MongoBookRepository extends MongoRepository<MongoBook, String> {

    List<MongoBook> findAll();

}
