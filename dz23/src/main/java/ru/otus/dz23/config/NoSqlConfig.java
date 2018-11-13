package ru.otus.dz23.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Collections;

import static java.util.Collections.singletonList;

@Configuration
@ConfigurationProperties("application")
public class NoSqlConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.host}")
    private String databaseAddress;

    @Value("${spring.data.mongodb.port}")
    private Integer databasePort;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;
//    @Bean
//    public MongoDbFactory mongoDbFactory() throws Exception {
//        return new SimpleMongoDbFactory(new MongoClient(), databaseName);
//    }


//    @Override
//    public MongoClient mongoClient() {
//        return new MongoClient(new ServerAddress(databaseAddress, databasePort));
//    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(Collections.singletonList(new ServerAddress(databaseAddress, databasePort)),
//                Collections.singletonList(MongoCredential.createScramSha1Credential(username, databaseName, password.toCharArray())));
//                Collections.singletonList(MongoCredential.createScramSha1Credential("sergya2", databaseName, "54321".toCharArray())));
                Collections.singletonList(MongoCredential.createScramSha1Credential("userbatch", databaseName, "batchbatch".toCharArray())));
    }

//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
//        return mongoTemplate;
//    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(new SimpleMongoDbFactory(mongoClient(), databaseName));
    }

//    @Bean
//    public Mongo mongo() throws Exception {
//        return new MongoClient(singletonList(new ServerAddress(databaseAddress, databasePort)),
//                singletonList(MongoCredential.createCredential(username,databaseName, password.toCharArray())));
//    }

//    @Autowired
//    public Environment env;
//
//    @Override
//    public MongoClient mongoClient() {
//        return new MongoClient(Collections.singletonList(
//                new ServerAddress(env.getRequiredProperty("spring.data.mongodb.host"),
//                        Integer.valueOf(env.getRequiredProperty("spring.data.mongodb.port")))),
//                Collections.singletonList(
//                        MongoCredential.createCredential(env.getRequiredProperty("spring.data.mongodb.username"),
//                                "admin", env.getRequiredProperty("spring.data.mongodb.password").toCharArray())));
//        //MongoCredential.createCredential("sergya", "admin", "otusotus".toCharArray())));
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return env.getRequiredProperty("spring.data.mongodb.database");
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate(){
//        return new MongoTemplate(new SimpleMongoDbFactory(mongoClient(), env.getRequiredProperty("spring.data.mongodb.database")));
//    }
}
