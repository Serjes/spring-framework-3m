package ru.otus.dz19.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.dz19.domain.Author;
import ru.otus.dz19.domain.Book;
import ru.otus.dz19.domain.BookDto;
import ru.otus.dz19.domain.Genre;
import ru.otus.dz19.repository.UserRepository;
import ru.otus.dz19.security.SecurityConfiguration;
import ru.otus.dz19.service.CommentService;
import ru.otus.dz19.service.LibraryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AddBookController.class)
@WithMockUser(
        username = "admin",
        authorities = {"ROLE_ADMIN"}

)
public class AddBookControllerRoleAdminTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CommentService commentService;

    @Configuration
    @ComponentScan(basePackageClasses = {AddBookController.class, SecurityConfiguration.class})
    public static class TestConf {
    }

    private BookDto bookDto;
    private Author author;
    private Genre genre;
    private Book book;
    private List<Book> books;

    @Before
    public void setUp() throws Exception {
        bookDto = new BookDto(1, "Мертвые души", "Николай", "Гоголь", "поэма");
        author = new Author("Лев", "Толстой");
        author.setId(1);
        genre = new Genre("роман-эпопея");
        genre.setId(1);
        book = new Book("Война и мир", author, genre);
        book.setId(1);
        books = Arrays.asList(book);
    }

    @Test
    public void addBookPage() throws Exception {
        mvc.perform(get("/addbook"))
                .andExpect(status().isOk());
    }

    @Test
    public void editBookPage() throws Exception {
        Mockito.when(libraryService.findBookById(1)).thenReturn(Optional.of(book));
        mvc.perform(get("/addbook/edit?id=" + book.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString(book.getTitle())));
    }
}
