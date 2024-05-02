package br.challenge.ximple;

import br.challenge.ximple.domain.adapters.repository.BookRepository;
import br.challenge.ximple.infrastracture.entities.Book;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTests {

	private final MockMvc mockMvc;
	private final BookRepository bookRepository;

	@Autowired
	public BookControllerTests(MockMvc mockMvc, BookRepository bookRepository) {
		this.bookRepository = bookRepository;
		this.mockMvc = mockMvc;
	}

	@Test
	void testSearchBooks_isOK() throws Exception {
		this.mockMvc.perform(get("/book/search"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void testSearchBooks_resultSize() throws Exception {
		var mvcResult = this.mockMvc.perform(get("/book/search"))
				.andExpect(status().isOk())
				.andReturn();


		List<Book> books = bookRepository.listAll();
		final int expectedSize = books.size();

		final int size = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.length()");
		Assertions.assertEquals(expectedSize, size);
	}



}
