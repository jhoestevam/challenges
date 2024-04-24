package br.challenge.ximple.domain.adapters.repository;

import br.challenge.ximple.domain.adapters.CreateReservationBook;
import br.challenge.ximple.infrastracture.entities.BookReservation;

import java.util.UUID;

public interface BookReservationRepository {
    UUID save(final BookReservation bookReservation);

    void delete(BookReservation bookReservation);
}
