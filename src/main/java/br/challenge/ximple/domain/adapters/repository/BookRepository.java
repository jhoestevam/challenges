package br.challenge.ximple.domain.adapters.repository;

import br.challenge.ximple.infrastracture.entities.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

    List<Book> listAll();

    Optional<Book> findById(String id);

    UUID save(Book book);
}
