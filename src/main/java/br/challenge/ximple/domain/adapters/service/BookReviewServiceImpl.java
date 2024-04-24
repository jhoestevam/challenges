package br.challenge.ximple.domain.adapters.service;

import br.challenge.ximple.domain.adapters.CreateAndSearchReview;
import br.challenge.ximple.domain.adapters.repository.BookRepository;
import br.challenge.ximple.domain.adapters.repository.BookReviewRepository;
import br.challenge.ximple.infrastracture.entities.Book;
import br.challenge.ximple.infrastracture.entities.BookReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookReviewServiceImpl implements BookReviewService {
    private final BookRepository bookRepository;
    private final BookReviewRepository bookReviewRepository;

    @Autowired
    public BookReviewServiceImpl(final BookRepository bookRepository, final BookReviewRepository bookReviewRepository) {
        this.bookRepository = bookRepository;
        this.bookReviewRepository = bookReviewRepository;
    }

    //todo: allEntries is not the best option as it can lead to a significant performance hit if the cache is large.
    @Override
    @CacheEvict(value = "listAllBooksForSearch", allEntries = true)
    public UUID createReview(CreateAndSearchReview createAndSearchReview) {
        return bookRepository.findById(createAndSearchReview.bookId())
                .map(book -> {
                    final var bookReview = new BookReview();
                    bookReview.setUuid(UUID.randomUUID().toString());
                    bookReview.setAuthor(createAndSearchReview.author());
                    bookReview.setReview(createAndSearchReview.review());
                    bookReview.setRating(createAndSearchReview.rating());
                    bookReview.setBook(book);
                    return bookReviewRepository.save(bookReview);
                }).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }
}
