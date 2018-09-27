package ru.otus.dz19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.dz19.domain.Book;
import ru.otus.dz19.domain.BookDto;
import ru.otus.dz19.service.LibraryService;

import java.util.List;

@Controller
public class BookController {

    private final LibraryService libraryService;

    @Autowired
    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public String booksPage(Model model) {
        List<Book> books = libraryService.listBooks();
        model.addAttribute("books", books);
        BookDto bookDto = new BookDto();
        model.addAttribute("bookDto", bookDto);
        return "books";
    }

//    @PostMapping("/books/delete/")
    @RequestMapping(
            value = {"/books/delete/"},
            method = RequestMethod.POST
    )
    public String delete(
            @ModelAttribute("bookDto") BookDto bookDto
    ) {
        libraryService.delBook(bookDto.getId());
        return "redirect:/books";
    }

    @RequestMapping(
            value = {"/books/add"},
            method = RequestMethod.POST
    )
    public String saveBook(
            @ModelAttribute("bookDto") BookDto bookDto
    ) {
        libraryService.addBook(bookDto.getBookTitle(),  bookDto.getAuthorName(),
                bookDto.getAuthorLastName(), bookDto.getGenre());

        return "redirect:/books";
    }

    @RequestMapping(
            value = {"/books/add/{id}"},
            method = RequestMethod.POST
    )
    public String updateBook(
            Model model,
            @ModelAttribute("bookDto") BookDto bookDto,
            @PathVariable("id") Integer id
    ) {
        libraryService.updateBook(id, bookDto.getBookTitle(),
                bookDto.getAuthorName(), bookDto.getAuthorLastName(),
                bookDto.getGenre());
        return "redirect:/books";
    }
}
