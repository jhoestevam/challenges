package br.challenge.ximple.application.controller;

import br.challenge.ximple.domain.adapters.CreateAndSearchReview;
import br.challenge.ximple.domain.adapters.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/review")
public class BookReviewController {
    private final BookReviewService bookReviewService;

    @Autowired
    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> createReview(@RequestBody final CreateAndSearchReview createAndSearchReview) {
        return ResponseEntity.ok(bookReviewService.createReview(createAndSearchReview));
    }
}
