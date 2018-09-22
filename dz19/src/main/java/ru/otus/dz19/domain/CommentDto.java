package ru.otus.dz19.domain;

public class CommentDto {

    private Integer id;
    private String commentContent;
    private String bookTitle;
    private Integer bookId;

    public CommentDto() {
    }

    public CommentDto(Integer id, String commentContent, String bookTitle, Integer bookId) {
        this.id = id;
        this.commentContent = commentContent;
        this.bookTitle = bookTitle;
        this.bookId = bookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
