package br.challenge.ximple.domain.adapters.repository;

import br.challenge.ximple.infrastracture.entities.BookReview;

import java.util.UUID;

public interface BookReviewRepository {

    UUID save(BookReview bookReview);
}
