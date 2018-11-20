package ru.otus.dz23;

import com.mongodb.MongoClient;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.*;

//@Configuration
////@EnableBatchProcessing
//public class BatchConfiguration {
////
////    @Bean
////    public JobLauncherTestUtils jobLauncherTestUtils() {
////        return new JobLauncherTestUtils();
//////        {
//////            @Override
//////            @Autowired
//////            public void setJob( Job job) {
//////                super.setJob(job);
//////            }
//////        };
////    }
//}
@TestConfiguration
//@Import({EmbeddedMongoAutoConfiguration.class, ru.otus.dz23.MongoTestConfiguration.class})
@Import(EmbeddedMongoAutoConfiguration.class)
//@ComponentScan("ru.otus.dz23")
//@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
//@AutoConfigureBefore(EmbeddedMongoAutoConfiguration.class)
public class BatchConfiguration
{
    @Bean
    @DependsOn("embeddedMongoServer")
    public MongoClient mongoClient(MongoTestConfiguration mongoTestConfiguration) {
        return new MongoClient(mongoTestConfiguration.getHost(),
                mongoTestConfiguration.getPort());
    }

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils()
    {
        return new JobLauncherTestUtils();
    }
}