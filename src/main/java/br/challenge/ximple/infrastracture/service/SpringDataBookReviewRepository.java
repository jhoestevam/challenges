package br.challenge.ximple.infrastracture.service;

import br.challenge.ximple.infrastracture.entities.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataBookReviewRepository extends JpaRepository<BookReview, String> {
}
