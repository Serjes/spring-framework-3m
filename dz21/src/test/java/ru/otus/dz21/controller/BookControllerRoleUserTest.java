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
import ru.otus.dz21.domain.Author;
import ru.otus.dz21.domain.Book;
import ru.otus.dz21.domain.Comment;
import ru.otus.dz21.domain.Genre;
import ru.otus.dz21.dto.BookDto;
import ru.otus.dz21.repository.AuthorRepository;
import ru.otus.dz21.repository.BookRepository;
import ru.otus.dz21.repository.GenreRepository;
import ru.otus.dz21.repository.UserRepository;
import ru.otus.dz21.service.CommentService;
import ru.otus.dz21.service.LibraryService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableWebMvc
@WithMockUser(
//        username = "advuser",
        username = "user",
        authorities = {"ROLE_USER"}
//        authorities = {"ROLE_ADVANCED_USER"}
)
public class BookControllerRoleUserTest {

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
        author.setId(1);
        authorRepositoryJpa.save(author);

        genre = new Genre("роман-эпопея");
        genre.setId(1);
        genreRepositoryJpa.save(genre);

        book = new Book("Война и мир", author, genre);
        book.setId(1);

        bookRepositoryJpa.save(book);
//        libraryService.saveBook(book);

        books = Arrays.asList(book);
        bookDto = new BookDto(3, "Мертвые души", "Николай", "Гоголь", "поэма");

    }

    @Test
    public void booksPage() throws Exception {
//        Mockito.when(libraryService.listBooks()).thenReturn(books);
        mvc.perform(get("/books"))
//                .andExpect(status().isForbidden())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(not(containsString(book.getTitle()))))
//                .andExpect(content().string(containsString(book.getTitle())))
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