package ru.otus.dz23.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Collections;

@Configuration
@ConfigurationProperties("application")
//@ConfigurationProperties(prefix="spring.data.mongodb")
public class NoSqlConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
//    private String databaseName;
    private String database;

    @Value("${spring.data.mongodb.host}")
//    private String databaseAddress;
    private String host;

    @Value("${spring.data.mongodb.port}")
//    private Integer databasePort;
    private Integer port;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Override
    public MongoClient mongoClient() {
//        return new MongoClient(Collections.singletonList(new ServerAddress(databaseAddress, databasePort)),
        return new MongoClient(Collections.singletonList(new ServerAddress(host, port)),
//                Collections.singletonList(MongoCredential.createCredential(username, databaseName, password.toCharArray())));
                Collections.singletonList(MongoCredential.createCredential(username, database, password.toCharArray())));
//                Collections.singletonList(MongoCredential.createCredential("userbatch", databaseName, "batchbatch".toCharArray())));
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(new SimpleMongoDbFactory(mongoClient(), database));
    }

}
