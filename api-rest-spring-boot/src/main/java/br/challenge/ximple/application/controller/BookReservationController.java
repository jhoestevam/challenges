package br.challenge.ximple.application.controller;

import br.challenge.ximple.domain.adapters.CreateReservationBook;
import br.challenge.ximple.domain.adapters.service.BookReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class BookReservationController {

    private final BookReservationService bookReservationService;

    @Autowired
    public BookReservationController(final BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> reserveBook(@RequestBody final CreateReservationBook createReservationBook) {
        return ResponseEntity.ok(bookReservationService.createReservation(createReservationBook));
    }

    @PostMapping(value = "/delivery/{book_id:[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deliveryBook(@PathVariable("book_id") final UUID bookId) {
        bookReservationService.deliveryBook(bookId);
        return ResponseEntity.ok().body(true);
    }

}
