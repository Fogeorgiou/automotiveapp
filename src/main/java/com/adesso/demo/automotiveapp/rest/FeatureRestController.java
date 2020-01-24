package com.adesso.demo.automotiveapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.adesso.demo.automotiveapp.entity.Feature;
import com.adesso.demo.automotiveapp.service.FeatureService;

@RestController
public class FeatureRestController {

	private FeatureService featureService;
	
	@Autowired
	public FeatureRestController(FeatureService aFeatureService) {
		featureService = aFeatureService;
	}
	
	@GetMapping("/features")
	public List<Feature> getFeatures() {
		return featureService.findAll();
	}
	
	@GetMapping("/features/{featureId}")
	public Feature getFeature(@PathVariable int featureId) {
		return featureService.findById(featureId);
	}
}
