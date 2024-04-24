package br.challenge.ximple.domain.adapters.service;

import br.challenge.ximple.domain.adapters.SearchBook;
import br.challenge.ximple.infrastracture.entities.Book;

import java.util.List;

public interface BookService {

    List<SearchBook> searchBooks();

}
