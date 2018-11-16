package ru.otus.dz23;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.dz23.config.NoSqlConfig;

import java.util.Collections;

@Configuration
//@SpringBootApplication(exclude = {NoSqlConfig.class})
//@DataMongoTest
//@Import(EmbeddedMongoAutoConfiguration.class)
//@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "ru.otus.dz23.mongorepository")
public class MongoTestConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;
//
    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private Integer port;
//
//    @Value("${spring.data.mongodb.username}")
//    private String username;
//
//    @Value("${spring.data.mongodb.password}")
//    private String password;

//    @Bean
//    public MongoClient mongoClient() {
//        return new MongoClient(Collections.singletonList(new ServerAddress(host, port)),
//                Collections.singletonList(MongoCredential.createCredential(username, database, password.toCharArray())));
//    }
//    @Bean
//    public MongoClient mongoClient(){
//        return new MongoClient(host, port);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate(){
//        return new MongoTemplate(new SimpleMongoDbFactory(mongoClient(), database));
//    }

//    @Bean
//    public JobLauncherTestUtils jobLauncherTestUtils() {
//        return new JobLauncherTestUtils();
//    }

    @Override
    public MongoClient mongoClient() {
//        return null;
        return new MongoClient(host, port);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
