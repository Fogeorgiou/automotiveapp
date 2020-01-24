package com.adesso.demo.automotiveapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.adesso.demo.automotiveapp.entity.Model;
import com.adesso.demo.automotiveapp.service.ModelService;

@RestController
public class ModelRestController {

	private ModelService modelService;
	
	@Autowired
	public ModelRestController(ModelService aModelService) {
		modelService = aModelService;
	}
	
	@GetMapping("/models")
	public List<Model> getModels() {
		return modelService.findAll();
	}
	
	@GetMapping("/models/{modelId}")
	public Model getModel(@PathVariable int modelId) {
		return modelService.findById(modelId);
	}
}
