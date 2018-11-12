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
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.dz23.domain.Book;
import ru.otus.dz23.domain.MongoAuthor;
import ru.otus.dz23.domain.MongoBook;
import ru.otus.dz23.repository.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private BookRepository bookRepositoryJpa;

//    @Autowired
//    private MongDbBookRepository mongoBookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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
    public Step step1(ItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .chunk(3)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(Object o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) {
                        logger.info("Начало записи");
                    }

                    public void afterWrite(List list) {
                        logger.info("Конец записи");
                    }

                    public void onWriteError(Exception e, List list) {
                        logger.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {
                        logger.info("Начало обработки");
                    }

                    public void afterProcess(Object o, Object o2) {
                        logger.info("Конец обработки");
                    }

                    public void onProcessError(Object o, Exception e) {
                        logger.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        logger.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        logger.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        logger.info("Ошибка пачки");
                    }
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
//                MongoBook mongoBook = new MongoBook(book.getTitle(), book.getAuthor(), book.getGenre());
                MongoBook mongoBook = new MongoBook(book.getTitle(), null, null);
//                mongoBook

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
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("id", Sort.Direction.ASC);
        return new RepositoryItemReaderBuilder<Book>()
                .repository(bookRepositoryJpa)
                .sorts(sort)
                .methodName("findAll")
                .saveState(false)
                .build();
    }


    @Bean
    public ItemWriter<MongoBook> writer() {
        MongoItemWriter<MongoBook> writer = new MongoItemWriter<>();
        try {
//            writer.setTemplate(mongoTemplate());
            writer.setTemplate(mongoTemplate);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        writer.setCollection("books");
        return writer;
    }

}

