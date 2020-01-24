package com.adesso.demo.automotiveapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adesso.demo.automotiveapp.dao.ModelRepository;
import com.adesso.demo.automotiveapp.entity.Model;

@Service
public class ModelServiceImpl implements ModelService {

	private ModelRepository modelRepository;
	
	@Autowired
	public ModelServiceImpl(ModelRepository aModelRepository) {
		modelRepository = aModelRepository;
	}
	
	@Override
	public List<Model> findAll() {
		return modelRepository.findAll();
	}

	@Override
	public Model findById(int id) {
		return modelRepository.findById(id).orElseThrow();
	}
	
	@Override
	public void save(Model aModel) {
		modelRepository.save(aModel);
	}
	
	@Override
	public void deleteById(int anId) {
		modelRepository.deleteById(anId);
	}
}
