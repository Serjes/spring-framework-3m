package ru.otus.dz23.mongorepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.dz23.domain.MongoAuthor;
import ru.otus.dz23.domain.MongoBook;

public interface MongoAuthorRepository extends MongoRepository<MongoAuthor, String> {

    MongoAuthor save(MongoAuthor mongoAuthor);

}
