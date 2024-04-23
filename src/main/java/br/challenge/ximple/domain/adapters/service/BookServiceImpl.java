package br.challenge.ximple.domain.adapters.service;

import br.challenge.ximple.domain.adapters.CreateBook;
import br.challenge.ximple.domain.adapters.repository.BookRepository;
import br.challenge.ximple.infrastracture.entities.Book;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<CreateBook> searchBooks() {
        List<Book> books = bookRepository.listAll();
        return Streams.of(books)
                .map(book -> {
                    CreateBook createBook = new CreateBook();
                    createBook.setTitle(book.getTitle());
                    createBook.setAuthor(book.getAuthor());
                    return createBook;
                }).toList();
    }
}
