package br.challenge.ximple.application.controller;

import br.challenge.ximple.domain.adapters.CreateBook;
import br.challenge.ximple.domain.adapters.service.BookService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CreateBook> searchBooks() {
        List<CreateBook> createBooks = bookService.searchBooks();
        return createBooks;
    }

    @PostMapping("/{id}/review")
    public Void publishReview() {
        throw new NotImplementedException("Not implemented yet");
    }

    @PostMapping("/{id}/reserve")
    public Void reserveBook() {
        throw new NotImplementedException("Not implemented yet");
    }



}
