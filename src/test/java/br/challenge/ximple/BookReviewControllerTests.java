package br.challenge.ximple;

import br.challenge.ximple.domain.adapters.CreateAndSearchReview;
import br.challenge.ximple.domain.adapters.CreateReservationBook;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookReviewControllerTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    private static final String BOOK_ID = "550e8400-e29b-41d4-a716-446655440000";

    @Autowired
    public BookReviewControllerTests(final MockMvc mockMvc, final ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @Order(1)
    public void createReview_isOk() throws Exception {
        var createAndSearchReview = new CreateAndSearchReview(BOOK_ID, "John Doe", "This is a review", 5);

        var result = this.mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAndSearchReview)))
                .andExpect(status().isOk()).andReturn();

        var uuid = result.getResponse().getContentAsString();
        Assertions.assertNotNull(uuid);
    }


    @Test
    @Order(2)
    public void searchReview_bySearchBookEndpoint() throws Exception {
        var result = this.mockMvc.perform(get("/book/search"))
                .andExpect(status().isOk())
                .andReturn();

        final var contentAsString = result.getResponse().getContentAsString();

        var jsonReview = JsonPath.read(contentAsString, "$[*].reviews[*]");
        var reviews = objectMapper.convertValue(jsonReview, new TypeReference<List<CreateAndSearchReview>>() {});
        Assertions.assertEquals(1, reviews.size());

        final var firstReview = reviews.get(0);
        Assertions.assertEquals(BOOK_ID, firstReview.bookId());
    }
}
