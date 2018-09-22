package ru.otus.dz19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.dz19.domain.Book;
import ru.otus.dz19.domain.Comment;
import ru.otus.dz19.domain.CommentDto;
import ru.otus.dz19.service.CommentService;
import ru.otus.dz19.service.LibraryService;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final LibraryService libraryService;

    @Autowired
    public CommentController(CommentService commentService, LibraryService libraryService) {
        this.commentService = commentService;
        this.libraryService = libraryService;
    }

    @GetMapping("/comments")
    public String commentsPage(Model model) {
        List<Comment> allComments = commentService.listComments();
        model.addAttribute("comments", allComments);
        return "comments";
    }

    @GetMapping("/comments/list")
    public String commentsByBookPage(
            @RequestParam("id") Integer id,
            Model model
    ) {
        Optional<Book> optionalBook = libraryService.findBookById(id);
        if (optionalBook.isPresent()) {
            List<Comment> allByBook = commentService.listCommentsByBook(id);
            model.addAttribute("comments", allByBook);
            model.addAttribute("bookTitle", optionalBook.get().getTitle());
            CommentDto commentDto = new CommentDto();
            commentDto.setBookId(id);
            model.addAttribute("commentDto", commentDto);
            model.addAttribute("bookId", optionalBook.get().getId());
        }
        return "comments";
    }

    @RequestMapping(
            value = {"/comments/add"},
            method = RequestMethod.POST
    )
    public String saveBook(
            @ModelAttribute("commentDto") CommentDto commentDto
    ) {
        commentService.add(commentDto.getCommentContent(), commentDto.getBookId());
        return "redirect:/comments/list?id=" + commentDto.getBookId();
    }

    @RequestMapping(
            value = {"/comments/add/{id}"},
            method = RequestMethod.POST
    )
    public String updateBook(
            @ModelAttribute("commentDto") CommentDto commentDto,
            @PathVariable("id") Integer id
    ) {
        commentService.updateComment(id, commentDto.getCommentContent());
        return "redirect:/comments/list?id=" + commentDto.getBookId();
    }

    @PostMapping("/comments/delete/")
    public String delete(
            @ModelAttribute("commentDto") CommentDto commentDto
    ) {
        Optional<Comment> optionalComment = commentService.findCommentById(commentDto.getId());
        int id = optionalComment.get().getBook().getId();
        commentService.deleteComment(commentDto.getId());
        return "redirect:/comments/list?id=" + id;
    }
}
