package ru.otus.dz23.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.dz23.domain.Book;
import ru.otus.dz23.domain.MongoBook;
import ru.otus.dz23.repository.BookRepository;
import ru.otus.dz23.repository.MongoBookRepository;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    BookRepository bookRepositoryJpa;

    @Autowired
    MongoBookRepository mongoBookRepository;

    @Bean
    public Job importLibJob(Step step1) {
        return jobBuilderFactory.get("importLibJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step step1(FlatFileItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .chunk(3)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() { logger.info("Начало чтения"); }
                    public void afterRead(Object o) { logger.info("Конец чтения"); }
                    public void onReadError(Exception e) { logger.info("Ошибка чтения"); }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) { logger.info("Начало записи"); }
                    public void afterWrite(List list) { logger.info("Конец записи"); }
                    public void onWriteError(Exception e, List list) { logger.info("Ошибка записи"); }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {logger.info("Начало обработки");}
                    public void afterProcess(Object o, Object o2) {logger.info("Конец обработки");}
                    public void onProcessError(Object o, Exception e) {logger.info("Ошбка обработки");}
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {logger.info("Начало пачки");}
                    public void afterChunk(ChunkContext chunkContext) {logger.info("Конец пачки");}
                    public void afterChunkError(ChunkContext chunkContext) {logger.info("Ошибка пачки");}
                })
                .build();

    }

    @Bean
    public ItemProcessor processor() {
        return new ItemProcessor<Book, MongoBook>() {
            @Override
            public MongoBook process(Book book) throws Exception {
//                person.onBirthDay();
//                person.setName("Мистер " + person.getName());
                return mongoBook;
            }
        };
    }

//    @Bean
//    public RepositoryItemWriter<WagerActivity> writer() {
//        RepositoryItemWriter<WagerActivity> writer = new RepositoryItemWriter<>();
//        writer.setRepository(wagerActivityRepository);
//        writer.setMethodName("save");
//        return writer;
//    }

    @Bean
    public RepositoryItemReader<Book> reader() {
        return new RepositoryItemReaderBuilder<Book>()
                .methodName("findAll")
                .repository(bookRepositoryJpa)
                .build();
    }

    @Bean
//    public RepositoryItemWriter<MongoBook> mongoAuthorItemWriter(MongoBookRepository mongoBookRepository) {
    public RepositoryItemWriter<MongoBook> mongoBookItemWriter() {
        return new RepositoryItemWriterBuilder<MongoBook>()
                .repository(mongoBookRepository)
                .methodName("save")
                .build();
    }

}

