package br.challenge.ximple.infrastracture.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "book_review")
public class BookReview {

    @Id
    @Column(name = "id")
    private String uuid;

    @Column(name = "review")
    private String review;

    @Column(name = "author")
    private String author;

    @Column(name = "rating")
    @Min(1)
    @Max(10)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public BookReview() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
