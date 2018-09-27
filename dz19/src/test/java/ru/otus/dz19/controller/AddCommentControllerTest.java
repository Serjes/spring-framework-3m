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
import ru.otus.dz19.domain.Comment;
import ru.otus.dz19.domain.Genre;
import ru.otus.dz19.repository.UserRepository;
import ru.otus.dz19.security.SecurityConfiguration;
import ru.otus.dz19.service.CommentService;
import ru.otus.dz19.service.LibraryService;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AddCommentController.class)
@WithMockUser(
        username = "user"//,
        //authorities = {"USER"}
)
public class AddCommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CommentService commentService;


//    @ComponentScan(basePackageClasses = {AddCommentController.class, SecurityConfiguration.class})
    @Configuration
    @ComponentScan(basePackageClasses = {AddCommentController.class})
    public static class TestConf {
    }

    private Comment comment;
    private Author author;
    private Genre genre;
    private Book book;

    @Before
    public void setUp() throws Exception {
        author = new Author("Лев", "Толстой");
        author.setId(1);
        genre = new Genre("роман-эпопея");
        genre.setId(1);
        book = new Book("Война и мир", author, genre);
        book.setId(1);
        comment = new Comment("Эпично, но слишком затянуто.", book);
        comment.setId(1);
    }

    @Test
    public void addCommentPage() throws Exception {
        mvc.perform(get("/addcomment?id=" + book.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void editCommentPage() throws Exception {
        Mockito.when(commentService.findCommentById(1)).thenReturn(Optional.of(comment));
        mvc.perform(get("/addcomment/edit?id=" + comment.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(comment.getContent())));
    }
}