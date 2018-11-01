package ru.otus.dz21.controller;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.dz21.domain.*;
import ru.otus.dz21.dto.BookDto;
import ru.otus.dz21.repository.UserRepository;
import ru.otus.dz21.security.AclAuthorizationConfiguration;
import ru.otus.dz21.security.AclConfiguration;
import ru.otus.dz21.security.SecurityConfiguration;
import ru.otus.dz21.service.CommentService;
import ru.otus.dz21.service.LibraryService;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@WithMockUser(
//        username = "user",
        authorities = {"ROLE_USER"}
//        authorities = {UserRoleEnum}
)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private UserRepository userRepository;

//    @MockBean
//    @Autowired
//    private DataSource dataSource;

    @Configuration
    @ConfigurationProperties("application")
    @ComponentScan(basePackageClasses = {BookController.class, SecurityConfiguration.class,
            AclConfiguration.class, AclAuthorizationConfiguration.class})
    public static class TestConf {

//        private String url;
        @Bean
        DataSource dataSource() {
            HikariConfig dataSourceConfig = new HikariConfig();
            dataSourceConfig.setJdbcUrl("jdbc:h2:mem:testdb");
//            dataSourceConfig.setSchema("classpath:/schema.sql");
//            dataSourceConfig.set
//            dataSourceConfig.setData
//            dataSourceConfig.setJdbcUrl(spring.datasource.url);
//            dataSourceConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
//            dataSourceConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));
            return new HikariDataSource(dataSourceConfig);
        }
    }

    private Author author;
    private Genre genre;
    private Book book;
    private Comment comment;
    private List<Book> books;
    private BookDto bookDto;

    @Before
    public void setUp() throws Exception {
        author = new Author("Лев", "Толстой");
        author.setId(1);
        genre = new Genre("роман-эпопея");
        genre.setId(1);
        book = new Book("Война и мир", author, genre);
        comment = new Comment("Эпично, но слишком затянуто.", book);
        book.setId(1);
        books = Arrays.asList(book);
        bookDto = new BookDto(1, "Мертвые души", "Николай", "Гоголь", "поэма");

    }

    @Test
    public void booksPage() throws Exception {
        Mockito.when(libraryService.listBooks()).thenReturn(books);
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString(book.getTitle())))
                .andExpect(view().name("books"));
    }

//    @Test
//    public void delete() throws Exception {
//        mvc.perform(post("/books/delete/").flashAttr("bookDto", bookDto))
//                .andExpect(status().isForbidden());
//
//    }
//
//    @Test
//    public void saveBook() throws Exception {
//        mvc.perform(post("/books/add")
//                .flashAttr("bookDto", bookDto))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void updateBook() throws Exception {
//        mvc.perform(post("/books/add/1")
//                .flashAttr("bookDto", bookDto))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void addBookPage() throws Exception {
//        mvc.perform(get("/addbook"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void editBookPage() throws Exception {
//        mvc.perform(get("/addbook/edit?id=1" ))
//                .andExpect(status().isForbidden());
//    }
}