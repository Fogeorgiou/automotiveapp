package com.adesso.demo.automotiveapp.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adesso.demo.automotiveapp.rest.request.PricingRequest;
import com.adesso.demo.automotiveapp.service.PricingService;

@RestController
public class PricingRestController {

	private PricingService pricingService;
	
	@Autowired
	public PricingRestController(PricingService aPricingService) {
		pricingService = aPricingService;
	}
	
	@GetMapping("/pricing")
	public BigDecimal getTotalPrice(@RequestBody PricingRequest pricingRequest) {
		
		if (pricingRequest.getModelId() == 0) {
			throw new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "Model Id must be provided and be greater than 0");
		}
		
		return pricingService.getPrice(pricingRequest.getModelId(), pricingRequest.getFeatureIds());
	}
}
