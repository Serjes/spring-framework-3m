package ru.otus.dz21.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
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
import ru.otus.dz21.service.CommentService;
import ru.otus.dz21.service.LibraryService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableWebMvc
@WithMockUser(
        username = "advuser",
        authorities = {"ROLE_ADVANCED_USER"}
)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

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

    }

    @Test
    public void booksPage() throws Exception {
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString(book.getTitle())))
                .andExpect(view().name("books"));
    }

}