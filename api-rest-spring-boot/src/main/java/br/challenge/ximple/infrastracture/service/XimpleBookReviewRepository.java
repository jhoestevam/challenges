package br.challenge.ximple.infrastracture.service;

import br.challenge.ximple.domain.adapters.repository.BookReviewRepository;
import br.challenge.ximple.infrastracture.entities.BookReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class XimpleBookReviewRepository implements BookReviewRepository {
    private final SpringDataBookReviewRepository bookReviewRepository;

    @Autowired
    public XimpleBookReviewRepository(final SpringDataBookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    @Override
    public UUID save(BookReview bookReview) {
        final var savedBookReview = bookReviewRepository.save(bookReview);
        return UUID.fromString(savedBookReview.getUuid());
    }
}
