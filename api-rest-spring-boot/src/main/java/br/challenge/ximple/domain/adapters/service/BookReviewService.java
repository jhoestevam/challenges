package br.challenge.ximple.domain.adapters.service;

import br.challenge.ximple.domain.adapters.CreateAndSearchReview;

import java.util.UUID;

public interface BookReviewService {

    UUID createReview(final CreateAndSearchReview createAndSearchReview);
}
