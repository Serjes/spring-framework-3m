package ru.otus.dz23;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import java.util.Collections;

@TestConfiguration
@DataMongoTest
//@Import(EmbeddedMongoAutoConfiguration.class)
//@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
public class SpringBatchTestConfiguration {

    //    @Bean
//    @DependsOn("embeddedMongoServer")
//    public MongoClient mongoClient(MongoConfigurationProperties mongoConfigurationProperties) {
//        return new MongoClient(mongoConfigurationProperties.getHost(),
//                mongoConfigurationProperties.getPort());
//    }
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(Collections.singletonList(new ServerAddress(host, port)),
                Collections.singletonList(MongoCredential.createCredential(username, database, password.toCharArray())));
    }

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

}
