package br.challenge.ximple.infrastracture.service;

import br.challenge.ximple.infrastracture.entities.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataBookReservationRepository extends JpaRepository<BookReservation, String> {
}
