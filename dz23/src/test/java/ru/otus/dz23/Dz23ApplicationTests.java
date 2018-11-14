package ru.otus.dz23;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dz23.config.BatchConfig;
import ru.otus.dz23.config.NoSqlConfig;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//@SpringBootTest
//@SpringBootTest(classes = BatchConfig.class)
@SpringBootTest(classes = SpringBatchTestConfiguration.class)
//@SpringApplicationConfiguration(classes = TestProductApplication.class)
//@EnableMongoRepositories
//@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.)
//@AutoConfigureDataMongo
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//@ComponentScan
//@Import(SpringBatchTestConfiguration.class)
public class Dz23ApplicationTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

//    @Autowired
//    MongoTemplate mongoTemplate;


    @Test
    public void launchJob() throws Exception {

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

    }
}
