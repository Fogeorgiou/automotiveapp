package com.adesso.demo.automotiveapp;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.adesso.demo.automotiveapp.dao.ModelRepository;
import com.adesso.demo.automotiveapp.entity.Model;
import com.adesso.demo.automotiveapp.rest.ModelRestController;
import com.adesso.demo.automotiveapp.service.ModelService;

@WebMvcTest(ModelRestController.class)
public class ModelServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private ModelRepository mockModelRepository;
	
	@MockBean
	private ModelService mockModelService;
	
	@Test
	public void shouldReturnModels() throws Exception {
		
		List<Model> models = new ArrayList<>();
		models.add(new Model("Golf", new BigDecimal("25000.00"), "Volkswagen", List.of("Seat Heating", "Climate Control")));
		models.add(new Model("Polo", new BigDecimal("35000.00"), "Volkswagen", List.of("Seat Heating", "Climate Control")));
		
		when(mockModelService.findAll()).thenReturn(models);
		
		this.mockMvc.perform(get("/models")).andDo(print())
		        .andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void shouldReturnModelWithSpecifiedId() throws Exception {
		
		when(mockModelService.findById(9))
			.thenReturn(new Model("Golf", new BigDecimal("25000.00"), "Volkswagen", List.of("Seat Heating", "Climate Control")));
		
		this.mockMvc.perform(get("/models/9")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Golf"))
				.andExpect(jsonPath("$.baseCost").value(25000.00))
				.andExpect(jsonPath("$.manufacturer").value("Volkswagen"))
				.andExpect(jsonPath("$.availableFeatures").isArray())
				.andExpect(jsonPath("$.availableFeatures", hasSize(2)))
				.andExpect(jsonPath("$.availableFeatures", hasItem("Seat Heating")))
				.andExpect(jsonPath("$.availableFeatures", hasItem("Climate Control")));
	}

	@Test
	public void shouldThrowExceptionWhenModelWithSpecifiedIdNotFound() throws Exception {
		
		when(mockModelService.findById(50))
			.thenThrow(new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Model Id: 50 not found"));
		
		this.mockMvc.perform(get("/models/50")).andDo(print())
				.andExpect(status().isNotFound());
	}
}
