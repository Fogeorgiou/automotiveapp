package com.adesso.demo.automotiveapp.rest;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
		try {
			return modelService.findById(modelId);
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Model Id: " + modelId + " not found", ex);
		}
	}
	
	@PostMapping("/models")
	public void addModel(@Valid @RequestBody Model aModel) {
		aModel.setId(0);
		modelService.save(aModel);
	}
	
	@DeleteMapping("/models/{modelId}")
	public void removeModel(@PathVariable int modelId) {
		try {
			modelService.deleteById(modelId);
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Model Id: " + modelId + " not found", ex);
		}
	}
	
	@PutMapping("/models/{modelId}")
	public Model updateModel(@PathVariable int modelId, @Valid @RequestBody Model aModel) {
		try {
			return modelService.update(modelId, aModel);
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Model Id: " + modelId + " not found", ex);
		}
	}
}
