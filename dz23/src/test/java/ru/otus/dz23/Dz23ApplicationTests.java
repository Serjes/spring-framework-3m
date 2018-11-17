package ru.otus.dz23;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
////@ExtendWith(SpringExtension.class)
////@DataMongoTest
////@SpringBootTest
////@SpringBootTest(classes = BatchConfig.class)
////@SpringBootTest(classes = {BatchTestConfiguration.class, SqlConfig.class})
////@SpringBootTest(classes = {MongoTestConfiguration.class, BatchConfiguration.class})
//@SpringBootTest(classes = {MongoTestConfiguration.class})
//@EnableJpaRepositories("ru.otus.dz23.repository")
////@EnableAutoConfiguration
////@EnableMongoRepositories(basePackages = "ru.otus.dz23.mongorepository")
////@EnableMongoRepositories(basePackages = "ru.otus.dz23.mongorepository")
////@SpringApplicationConfiguration(classes = TestProductApplication.class)
////@EnableMongoRepositories
////@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
////@Transactional(propagation = Propagation.NOT_SUPPORTED)
////@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.)
////@AutoConfigureDataMongo
////@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
////@ComponentScan
////@Import(SpringBatchTestConfiguration.class)
//public class Dz23ApplicationTests {
//
//    @Configuration
//    @EnableBatchProcessing
//    static class BatchTestConfig {
//
//        @Bean
//        JobLauncherTestUtils jobLauncherTestUtils() {
//            return new JobLauncherTestUtils(){
//                @Override
//                @Autowired
//                public void setJob(@Qualifier("importLibJob") Job job) {
//                    super.setJob(job);
//                }
//            };
//        }
//
//    }
//
//    @Autowired
//    private JobLauncherTestUtils jobLauncherTestUtils;
////    @Autowired
//
////    MongoTemplate mongoTemplate;
//
//
//    @Test
//    public void launchJob() throws Exception {
//
//        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
//
//        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
//
//    }
//}
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatchConfiguration.class)
public class Dz23ApplicationTests
{
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void jobTest()
    {
        try
        {
            JobExecution jobExecution = jobLauncherTestUtils.launchJob();
            assertThat(jobExecution.getExitStatus())
                    .isEqualTo(ExitStatus.COMPLETED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            assertThat(e).isNull();
        }
    }
}