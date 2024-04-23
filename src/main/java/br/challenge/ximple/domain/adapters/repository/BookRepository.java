package br.challenge.ximple.domain.adapters.repository;

import br.challenge.ximple.infrastracture.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository {

    List<Book> listAll();

}
