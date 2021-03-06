package ru.otus.dz21.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.dz21.service.LibraryService;

@Controller
public class IndexController {

    private final LibraryService libraryService;

    @Autowired
    public IndexController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("booksCount", libraryService.count());
        model.addAttribute("authorsCount", libraryService.countAuthors());
        model.addAttribute("genresCount", libraryService.countGenres());
        return "index";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
