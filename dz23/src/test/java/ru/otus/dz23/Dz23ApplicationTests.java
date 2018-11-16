package ru.otus.dz23;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//@SpringBootTest
//@SpringBootTest(classes = BatchConfig.class)
//@SpringBootTest(classes = {BatchTestConfiguration.class, SqlConfig.class})
@SpringBootTest(classes = {MongoTestConfiguration.class, BatchConfiguration.class})
//@EnableMongoRepositories(basePackages = "ru.otus.dz23.mongorepository")
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
