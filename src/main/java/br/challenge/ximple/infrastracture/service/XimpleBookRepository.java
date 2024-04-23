package br.challenge.ximple.infrastracture.service;

import br.challenge.ximple.domain.adapters.repository.BookRepository;
import br.challenge.ximple.infrastracture.entities.Book;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
