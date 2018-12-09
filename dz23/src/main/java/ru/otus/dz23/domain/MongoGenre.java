package ru.otus.dz23.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "genres")
public class MongoGenre {

    @Id
    private String id;
    @Field("name")
    private String name;
    private Set<MongoBook> mongoBooks = new HashSet<>();

    public MongoGenre() {
    }

    public MongoGenre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
