package br.challenge.ximple.domain.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateAndSearchReview ( @JsonProperty("book_id") String bookId,
                                      @JsonProperty("author_name") String author,
                                      @JsonProperty("review_description") String review,
                                      @JsonProperty("rating") int rating) {

}
