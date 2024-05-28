package br.challenge.ximple.infrastracture.service;

import br.challenge.ximple.domain.adapters.repository.BookReservationRepository;
import br.challenge.ximple.infrastracture.entities.BookReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class XimpleBookReservationRepository implements BookReservationRepository {

    private final SpringDataBookReservationRepository bookReservationRepository;

    @Autowired
    public XimpleBookReservationRepository(SpringDataBookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }

    @Override
    public UUID save(BookReservation bookReservation) {
        final var savedBookReservation = bookReservationRepository.save(bookReservation);
        return UUID.fromString(savedBookReservation.getId());
    }

    @Override
    public void delete(BookReservation bookReservation) {
        bookReservationRepository.delete(bookReservation);
    }
}
