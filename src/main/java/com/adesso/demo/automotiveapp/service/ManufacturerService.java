package com.adesso.demo.automotiveapp.service;

import java.util.List;

import com.adesso.demo.automotiveapp.entity.Manufacturer;
import com.adesso.demo.automotiveapp.entity.Model;

public interface ManufacturerService {

	public List<Manufacturer> findAll();
	
	public Manufacturer findById(int id);
	
	public List<Model> getModelsByManufacturerId(int manufacturerId);
}
