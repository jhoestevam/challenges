package br.challenge.ximple;

import br.challenge.ximple.domain.adapters.CreateReservationBook;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookReservationControllerTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    private static final String BOOK_ID = "550e8400-e29b-41d4-a716-446655440000";

    @Autowired
    public BookReservationControllerTests(final MockMvc mockMvc, final ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    @Test
    public void testCreateReserveBook() throws Exception {
        final CreateReservationBook createReservationBook = new CreateReservationBook(BOOK_ID,  "12345678900", LocalDate.now());

        var result = this.mockMvc.perform(post("/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createReservationBook)))
                .andExpect(status().isOk()).andReturn();

        var uuid = result.getResponse().getContentAsString();
        Assertions.assertNotNull(uuid);
    }

    @Test
    public void testDeliveryBook() throws Exception {
        this.mockMvc.perform(post("/reservation/delivery/{book_id}", BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> Assertions.assertTrue(Boolean.parseBoolean(result.getResponse().getContentAsString())));
    }
}
