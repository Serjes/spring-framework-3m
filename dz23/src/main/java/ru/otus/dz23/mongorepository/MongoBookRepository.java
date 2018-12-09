package ru.otus.dz23.mongorepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.dz23.domain.MongoBook;

public interface MongoBookRepository extends MongoRepository<MongoBook, String> {

    MongoBook save(MongoBook mongoBook);

}
