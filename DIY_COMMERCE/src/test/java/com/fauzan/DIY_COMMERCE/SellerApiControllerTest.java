package com.fauzan.DIY_COMMERCE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fauzan.DIY_COMMERCE.entity.Seller;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SellerApiControllerTest {

	static ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void validSellerCreation() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/api/seller").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper
						.writeValueAsString(new Seller("fauzan2@seller.commerce", "realfauzan98", "emptyPasswordFtw")));

		mockMvc.perform(request)
				.andExpectAll(status()
						.isCreated(),
						content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void givenAllEmptyFieldsThenError() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/api/seller").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper
						.writeValueAsString(new Seller("", "", "")));

		mockMvc.perform(request)
				.andExpectAll(status()
						.isBadRequest(),
						content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void givenAllNullFieldsThenError() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/api/seller").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper
						.writeValueAsString(new Seller(null, null, null)));

		mockMvc.perform(request)
				.andExpectAll(status()
						.isBadRequest(),
						content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void givenExistingEmailThenError() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/api/seller").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper
						.writeValueAsString(new Seller("radhi@seller.my", "username", "password")));

		mockMvc.perform(request)
				.andExpectAll(status()
						.isConflict(),
						content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void givenExistingUsernameThenError() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/api/seller").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper
						.writeValueAsString(new Seller("email@gmail.com", "not_radhifauzan", "password")));
		
		mockMvc.perform(request)
		.andExpectAll(status()
				.isConflict(),
				content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void givenInvalidEmailFormatThenError() throws Exception {
		
		 List<String> invalidEmailList = new ArrayList<>();
	        invalidEmailList.add("missingAtSign.com");
	        invalidEmailList.add("noUsername@");
	        invalidEmailList.add("@domain.com");
	        invalidEmailList.add("space in@email.com");
	        invalidEmailList.add("double@@sign@email.com");
	        invalidEmailList.add(".dotStarts@emai.com");
	        invalidEmailList.add("dotEnds@email.com.");
	        invalidEmailList.add("missingDomainPart@");
	        invalidEmailList.add("double..dots@email.com");
		
		for(String invalid: invalidEmailList) {
			RequestBuilder request = MockMvcRequestBuilders.post("/api/seller").contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper
							.writeValueAsString(new Seller(invalid, "test", "test")));

			mockMvc.perform(request)
					.andExpectAll(status()
							.isBadRequest(),
							content().contentType(MediaType.APPLICATION_JSON));
		}
	}
	
	@Test
	public void givenExistingSellerId_thenExpectStatusIsFound() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/api/seller/e9ef05bb-9a4a-446a-a3ce-919e08116b60");

		mockMvc.perform(request)
				.andExpectAll(status()
						.isFound(),
						content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void givenNonExistingSellerId_thenExpectStatusIsFound() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/api/seller/e9ef05bb-9a4a-333a-a3ce-919e08116b60");
		
		mockMvc.perform(request)
		.andExpectAll(status()
				.isNotFound(),
				content().contentType(MediaType.APPLICATION_JSON));
	}

}
