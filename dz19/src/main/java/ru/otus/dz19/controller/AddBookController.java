package ru.otus.dz19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.dz19.domain.Book;
import ru.otus.dz19.domain.BookDto;
import ru.otus.dz19.service.LibraryService;

import java.util.Optional;

@Controller
public class AddBookController {

    private final LibraryService libraryService;

    @Autowired
    public AddBookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/addbook")
    public String addBookPage(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("bookDto", bookDto);
        return "addbook";
    }

    @GetMapping("/addbook/edit")
    public String editBookPage(
            @RequestParam("id") Integer id,
            Model model
    ) {
        Optional<Book> bookOptional = libraryService.findBookById(id);
        if (bookOptional.isPresent()) {
            BookDto bookDto = BookDto.toDto(bookOptional.get());
            model.addAttribute("bookDto", bookDto);
        }
        return "addbook";
    }
}
