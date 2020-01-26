package com.adesso.demo.automotiveapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.adesso.demo.automotiveapp.entity.Manufacturer;
import com.adesso.demo.automotiveapp.entity.Model;
import com.adesso.demo.automotiveapp.service.ManufacturerService;

@RestController
public class ManufacturerRestController {

	private ManufacturerService manufacturerService;
	
	@Autowired
	public ManufacturerRestController(ManufacturerService aManufacturerService) {
		manufacturerService = aManufacturerService;
	}
	
	@GetMapping("/manufacturers")
	public List<Manufacturer> getManufacturers() {
		return manufacturerService.findAll();
	}
	
	@GetMapping("/manufacturers/{manufacturerId}")
	public Manufacturer getManufacturer(@PathVariable int manufacturerId) {
		return manufacturerService.findById(manufacturerId);
	}
	
	@GetMapping("/manufacturers/{manufacturerId}/models")
	public List<Model> getModelsByManufacturerId(@PathVariable int manufacturerId) {
		return manufacturerService.getModelsByManufacturerId(manufacturerId);
	}
}
