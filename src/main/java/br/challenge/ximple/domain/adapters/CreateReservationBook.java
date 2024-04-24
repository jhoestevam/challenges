package br.challenge.ximple.domain.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CreateReservationBook (@JsonProperty("book_id") String bookId,
                                     @JsonProperty("client_cnpj_cpf") String clientCpfCnpj,
                                     @JsonProperty("expiration_date") LocalDate expirationDate) {
}
