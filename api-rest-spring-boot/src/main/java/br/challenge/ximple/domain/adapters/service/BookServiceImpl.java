package br.challenge.ximple.domain.adapters.service;

import br.challenge.ximple.domain.adapters.CreateAndSearchReview;
import br.challenge.ximple.domain.adapters.SearchBook;
import br.challenge.ximple.domain.adapters.repository.BookRepository;
import br.challenge.ximple.infrastracture.entities.Book;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private static SearchBook mapToRecord(Book book) {
        final var reviews = book.getReviews()
                .stream()
                .map(review -> new CreateAndSearchReview(
                        book.getUuid(),
                        review.getAuthor(),
                        review.getReview(),
                        review.getRating()))
                .toList();

        LocalDate availableDate = null;
        if (book.getReservation() != null) {
            availableDate = book.getReservation().getExpirationDate().plusDays(1);
        }

        return new SearchBook(
                book.getUuid(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getGenre(),
                book.getLanguage(),
                book.getPublisher(),
                book.getPublicationDate(),
                book.getNumberOfPages(),
                book.isAvailable(),
                availableDate,
                book.getRatePerDay(),
                reviews
        );
    }

    @Override
    @Cacheable("listAllBooksForSearch")
    public List<SearchBook> searchBooks() {
        return Streams.of(bookRepository.listAll())
                .map(BookServiceImpl::mapToRecord)
                .toList();
    }
}
