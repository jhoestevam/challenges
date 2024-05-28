package br.challenge.ximple.infrastracture.service;

import br.challenge.ximple.domain.adapters.repository.BookRepository;
import br.challenge.ximple.infrastracture.entities.Book;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class XimpleBookRepository implements BookRepository {

    private final SpringDataBookRepository bookRepository;

    @Autowired
    public XimpleBookRepository(final SpringDataBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAll() {
        return Streams.of(bookRepository.findAll()).toList();
    }

    @Override
    public Optional<Book> findById(final String id) {
        return bookRepository.findById(id);
    }

    @Override
    public UUID save(Book book) {
        final var savedBook = bookRepository.save(book);
        return UUID.fromString(savedBook.getUuid());
    }
}
