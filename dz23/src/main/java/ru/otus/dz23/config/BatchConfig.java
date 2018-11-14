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
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import ru.otus.dz23.domain.*;
import ru.otus.dz23.mongorepository.MongoAuthorRepository;
import ru.otus.dz23.mongorepository.MongoBookRepository;
import ru.otus.dz23.repository.AuthorRepository;
import ru.otus.dz23.repository.BookRepository;

import java.util.HashMap;
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

    @Autowired
    private AuthorRepository authorRepositoryJpa;

    @Bean
    public Job importLibJob(Step step1, Step step2) {
        return jobBuilderFactory.get("importLibJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .next(step2)
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
    public Step step1(ItemWriter bookWriter) {
        return stepBuilderFactory.get("step1")
                .chunk(3)
                .reader(bookReader())
                .processor(bookProcessor())
                .writer(bookWriter)
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
    public Step step2(ItemWriter authorWriter) {
        return stepBuilderFactory.get("step2")
                .chunk(3)
                .reader(authorReader())
                .processor(authorProcessor())
                .writer(authorWriter)
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
    public ItemProcessor bookProcessor() {
        return new ItemProcessor<Book, MongoBook>() {
            @Override
            public MongoBook process(Book book) throws Exception {

                Author author = book.getAuthor();
                MongoAuthor mongoAuthor = new MongoAuthor(author.getFirstName(), author.getLastName());
                MongoBook mongoBook = new MongoBook(book.getTittle(), mongoAuthor, null);
                logger.info("Book: " + book.getTittle());
                return mongoBook;
            }
        };
    }

    @Bean
    public ItemProcessor authorProcessor() {
        return new ItemProcessor<Author, MongoAuthor>() {
            @Override
            public MongoAuthor process(Author author) throws Exception {
                MongoAuthor mongoAuthor = new MongoAuthor(author.getFirstName(),author.getLastName());
                logger.info("Author: " + author.getFirstName());
                return mongoAuthor;
            }
        };
    }

    @Bean
    public RepositoryItemReader<Book> bookReader() {
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
    public RepositoryItemReader<Author> authorReader() {
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("id", Sort.Direction.ASC);
        return new RepositoryItemReaderBuilder<Author>()
                .repository(authorRepositoryJpa)
                .sorts(sort)
                .methodName("findAll")
                .saveState(false)
                .build();
    }

    @Bean
    public RepositoryItemWriter<MongoBook> bookWriter(MongoBookRepository mongoBookRepository) {
        return new RepositoryItemWriterBuilder<MongoBook>()
                .repository(mongoBookRepository)
                .methodName("save")
                .build();
    }

    @Bean
    public RepositoryItemWriter<MongoAuthor> authorWriter(MongoAuthorRepository mongoAuthorRepository) {
        return new RepositoryItemWriterBuilder<MongoAuthor>()
                .repository(mongoAuthorRepository)
                .methodName("save")
                .build();
    }

}

