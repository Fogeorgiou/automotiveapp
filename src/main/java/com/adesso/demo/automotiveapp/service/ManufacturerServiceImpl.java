package com.adesso.demo.automotiveapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adesso.demo.automotiveapp.dao.ManufacturerRepository;
import com.adesso.demo.automotiveapp.dao.ModelRepository;
import com.adesso.demo.automotiveapp.entity.Manufacturer;
import com.adesso.demo.automotiveapp.entity.Model;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	private ManufacturerRepository manufacturerRepository;
	private ModelRepository modelRepository;
	
	@Autowired
	public ManufacturerServiceImpl(ManufacturerRepository aManufacturerRepository, ModelRepository aModelRepository) {
		manufacturerRepository = aManufacturerRepository;
		modelRepository = aModelRepository;
	}
	
	@Override
	public List<Manufacturer> findAll() {
		return manufacturerRepository.findAll();
	}

	@Override
	public Manufacturer findById(int id) {
		return manufacturerRepository.findById(id).orElseThrow();
	}
	
	@Override
	public List<Model> getModelsByManufacturerId(int manufacturerId) {
		
		String manufacturerName = findById(manufacturerId).getName();
		
		List<Model> models = modelRepository.findAll();
		
		return models.stream()
				.filter(model -> manufacturerName.equals(model.getManufacturer()))
				.collect(Collectors.toList());
	}

}
