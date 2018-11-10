package ru.otus.dz23.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "books")
public class MongoBook {

    @Id
    private String id;
    @Field("tittle")
    private String tittle;
    @DBRef
    private MongoAuthor mongoAuthor;
    @DBRef
    private MongoGenre mongoGenre;

    public MongoBook() {
    }

    public MongoBook(String tittle, MongoAuthor mongoAuthor, MongoGenre mongoGenre) {
        this.tittle = tittle;
        this.mongoAuthor = mongoAuthor;
        this.mongoGenre = mongoGenre;
    }

    public MongoAuthor getMongoAuthor() {
        return mongoAuthor;
    }

    public MongoGenre getMongoGenre() {
        return mongoGenre;
    }

    public String getTittle() {
        return tittle;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MongoBook{" +
                "tittle='" + tittle + '\'' +
                '}';
    }
}
