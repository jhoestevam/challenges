package br.challenge.ximple.infrastracture.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_reservation")
public class BookReservation {

    @Id
    @Column(name = "id")
    private String uuid;

    @Column(name = "client_cnpj_cpf")
    private String clientCnpjCpf;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public String getId() {
        return uuid;
    }

    public void setId(String uuid) {
        this.uuid = uuid;
    }

    public String getClientCnpjCpf() {
        return clientCnpjCpf;
    }

    public void setClientCnpjCpf(String clientCnpjCpf) {
        this.clientCnpjCpf = clientCnpjCpf;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
