package com.adesso.demo.automotiveapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.adesso.demo.automotiveapp.dao.FeatureRepository;
import com.adesso.demo.automotiveapp.dao.ModelRepository;
import com.adesso.demo.automotiveapp.entity.Feature;
import com.adesso.demo.automotiveapp.entity.Model;
import com.adesso.demo.automotiveapp.rest.PricingRestController;
import com.adesso.demo.automotiveapp.rest.request.PricingRequest;
import com.adesso.demo.automotiveapp.service.PricingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PricingRestController.class)
public class PricingServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private ModelRepository mockModelRepository;
	
	@MockBean
    private FeatureRepository mockFeatureRepository;
	
	@MockBean
	private PricingService mockPricingService;
	
	@Test
	public void shouldReturnPricing() throws Exception {
		
		Model model1 = new Model("Golf", new BigDecimal("25000.00"), "Volkswagen", List.of("Seat Heating", "Climate Control"));
		when(mockModelRepository.findById(1)).thenReturn(Optional.of(model1));
		
		Feature feature1 = new Feature("Seat Heating", new BigDecimal("100.00"), List.of("Golf"));
		feature1.setId(1);
		Feature feature2 = new Feature("Climate Control", new BigDecimal("200.00"), List.of("Golf"));
		feature2.setId(2);
		when(mockFeatureRepository.findById(1)).thenReturn(Optional.of(feature1));
		when(mockFeatureRepository.findById(2)).thenReturn(Optional.of(feature2));
		
		when(mockPricingService.getPrice(1, new HashSet<>(Arrays.asList(1, 2)))).thenReturn(new BigDecimal(25300.00));
		
		PricingRequest pricingRequest = new PricingRequest(1, new HashSet<>(Arrays.asList(1, 2)));
		String requestBody = (new ObjectMapper()).valueToTree(pricingRequest).toString();
		
		this.mockMvc
				.perform(get("/pricing").content(requestBody).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andDo(print())
		        .andExpect(status().isOk())
				.andExpect(jsonPath("$").value(25300.00));
	}

	@Test
	public void shouldThrowExceptionWhenModelWithSpecifiedIdNotFound() throws Exception {
		
		when(mockPricingService.getPrice(50, new HashSet<>(Arrays.asList(1, 2))))
			.thenThrow(new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Model Id: 50 not found"));
		
		PricingRequest pricingRequest = new PricingRequest(50, new HashSet<>(Arrays.asList(1, 2)));
		String requestBody = (new ObjectMapper()).valueToTree(pricingRequest).toString();
		
		this.mockMvc.perform(get("/pricing").content(requestBody).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
	}
}
