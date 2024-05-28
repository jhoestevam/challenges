package br.challenge.ximple.domain.adapters.service;

import br.challenge.ximple.domain.adapters.CreateReservationBook;

import java.util.UUID;

public interface BookReservationService {

    UUID createReservation(final CreateReservationBook createReservationBook);

    void deliveryBook(final UUID bookId);
}
