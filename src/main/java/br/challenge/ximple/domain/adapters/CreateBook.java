package br.challenge.ximple.domain.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBook {

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("description")
    private String description;

//    private String genre;
//    private String language;
//    private String publisher;
//    private String publicationDate;
//    private String numberOfPages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
