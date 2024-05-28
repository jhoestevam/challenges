package br.challenge.ximple.domain.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record SearchBook (@JsonProperty("id") String id,
                          @JsonProperty("title") String title,
                          @JsonProperty("author") String author,
                          @JsonProperty("description") String description,
                          @JsonProperty("genre") String genre,
                          @JsonProperty("language") String language,
                          @JsonProperty("publisher") String publisher,
                          @JsonProperty("publication_date") LocalDate publicationDate,
                          @JsonProperty("number_of_pages") int numberOfPages,
                          @JsonProperty("available") boolean available,
                          @JsonProperty("available_date") LocalDate avaliableDate,
                          @JsonProperty("rate_per_day") BigDecimal ratePerDay,
                          @JsonProperty("reviews") List<CreateAndSearchReview> searchReviews) {


}
