package ru.otus.dz21.controller;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.otus.dz21.domain.*;
import ru.otus.dz21.dto.BookDto;
import ru.otus.dz21.repository.AuthorRepository;
import ru.otus.dz21.repository.BookRepository;
import ru.otus.dz21.repository.GenreRepository;
import ru.otus.dz21.repository.UserRepository;
import ru.otus.dz21.security.AclAuthorizationConfiguration;
import ru.otus.dz21.security.AclConfiguration;
import ru.otus.dz21.security.SecurityConfiguration;
import ru.otus.dz21.service.CommentService;
import ru.otus.dz21.service.LibraryService;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableWebMvc
@WithMockUser(
//        username = "user",
        username = "advuser",
        authorities = {"ROLE_ADVANCED_USER"}
//        authorities = {"ROLE_USER"}
)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

//    @MockBean
    @Autowired
    private LibraryService libraryService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepositoryJpa;

    @Autowired
    private GenreRepository genreRepositoryJpa;

    @Autowired
    private AuthorRepository authorRepositoryJpa;

    private Author author;
    private Genre genre;
    private Book book;
    private Comment comment;
    private List<Book> books;
    private BookDto bookDto;

    @Before
    public void setUp() throws Exception {

        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        author = new Author("Лев", "Толстой");
        author.setId(2);
        authorRepositoryJpa.save(author);

        genre = new Genre("роман-эпопея");
        genre.setId(2);
        genreRepositoryJpa.save(genre);

        book = new Book("Война и мир", author, genre);
        book.setId(2);

        bookRepositoryJpa.save(book);
//        bookRepositoryJpa.flush();
//        libraryService.saveBook(book);

//        books = Arrays.asList(book);
//        bookDto = new BookDto(2, "Мертвые души", "Николай", "Гоголь", "поэма");

    }

    @Test
    public void booksPage() throws Exception {
//        Mockito.when(libraryService.listBooks()).thenReturn(books);
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString(book.getTitle())))
                .andExpect(view().name("books"));
    }

//    @Test
//    public void delete() throws Exception {
////        bookRepositoryJpa.save(book);
//        mvc.perform(post("/books/delete/")
//                .flashAttr("bookDto", bookDto))
//                .andExpect(redirectedUrl("/books"));
//
//    }

//    @Test
//    public void saveBook() throws Exception {
////        bookRepositoryJpa.save(book);
//        mvc.perform(post("/books/add")
//                .flashAttr("bookDto", bookDto))
//                .andExpect(status().is3xxRedirection());
//    }

//    @Test
//    public void updateBook() throws Exception {
//        mvc.perform(post("/books/add/1")
//                .flashAttr("bookDto", bookDto))
//                .andExpect(redirectedUrl("/books"));
//    }
//
//    @Test
//    public void addBookPage() throws Exception {
//        mvc.perform(get("/addbook"))
//                .andExpect(status().isOk());
//    }

//    @Test
//    public void editBookPage() throws Exception {
//        Mockito.when(libraryService.findBookById(1)).thenReturn(Optional.of(book));
//        mvc.perform(get("/addbook/edit?id=" + book.getId()))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().string(containsString(book.getTitle())));
//    }
}