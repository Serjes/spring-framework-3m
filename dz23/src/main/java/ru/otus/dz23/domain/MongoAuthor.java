package ru.otus.dz23.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "authors")
public class MongoAuthor {

    @Id
    private String id;
    @Field("firstName")
    private String firstName;
    @Field("lastName")
    private String lastName;

    public MongoAuthor() {
    }

    public MongoAuthor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

//    public List<MongoBook> getMongoBooks() {
//        return mongoBooks;
//    }

}
