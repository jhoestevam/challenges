package br.challenge.ximple.infrastracture.service;

import br.challenge.ximple.infrastracture.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataBookRepository extends JpaRepository<Book, String> {
}
