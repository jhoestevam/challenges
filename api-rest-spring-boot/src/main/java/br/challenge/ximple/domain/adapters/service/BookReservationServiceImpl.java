package br.challenge.ximple.domain.adapters.service;

import br.challenge.ximple.domain.adapters.CreateReservationBook;
import br.challenge.ximple.domain.adapters.repository.BookRepository;
import br.challenge.ximple.domain.adapters.repository.BookReservationRepository;
import br.challenge.ximple.infrastracture.entities.Book;
import br.challenge.ximple.infrastracture.entities.BookReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookRepository bookRepository;
    private final BookReservationRepository bookReservationRepository;

    @Autowired
    public BookReservationServiceImpl(final BookRepository bookRepository, final BookReservationRepository bookReservationRepository) {
        this.bookRepository = bookRepository;
        this.bookReservationRepository = bookReservationRepository;
    }

    //todo: allEntries is not the best option as it can lead to a significant performance hit if the cache is large.
    @Override
    @CacheEvict(value = "listAllBooksForSearch", allEntries = true)
    public UUID createReservation(CreateReservationBook createReservationBook) {
        return bookRepository.findById(createReservationBook.bookId())
                .map(book -> {
                    final var bookReservation = new BookReservation();
                    bookReservation.setId(UUID.randomUUID().toString());
                    book.setAvailable(false);
                    bookReservation.setBook(book);
                    bookReservation.setExpirationDate(createReservationBook.expirationDate());
                    bookReservation.setClientCnpjCpf(createReservationBook.clientCpfCnpj());
                    return bookReservationRepository.save(bookReservation);
                }).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    //todo: allEntries is not the best option as it can lead to a significant performance hit if the cache is large.
    @Override
    @Transactional
    @CacheEvict(value = "listAllBooksForSearch", allEntries = true)
    public void deliveryBook(UUID bookId) {
        bookRepository.findById(bookId.toString())
                .ifPresentOrElse(book -> {
                    final var bookReservation = book.getReservation();
                    bookReservationRepository.delete(bookReservation);

                    book.setAvailable(true);
                    book.setReservation(null);
                    bookRepository.save(book);
                }, () -> {
                    throw new BookNotFoundException("Book not found");
                });
    }
}
