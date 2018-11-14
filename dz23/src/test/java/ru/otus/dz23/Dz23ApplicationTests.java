package ru.otus.dz23;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dz23.config.BatchConfig;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@DataMongoTest
//@SpringBootTest(classes = BatchConfig.class)
@SpringBootTest(classes = SpringBatchTestConfiguration.class)
//@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
public class Dz23ApplicationTests {

    //    @Test
//    public void contextLoads() {
//    }
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
//    @Bean
//    public JobLauncherTestUtils jobLauncherTestUtils()
//    {
//        return new JobLauncherTestUtils();
//    }


    @Test
    public void launchJob() throws Exception {

        //testing a job
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        //Testing a individual step
        //JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");

        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

    }
}
