package com.adesso.demo.automotiveapp.service;

import java.util.List;

import com.adesso.demo.automotiveapp.entity.Model;

public interface ModelService {

	public List<Model> findAll();
	
	public Model findById(int id);
	
	public void save(Model aModel);
	
	public void deleteById(int id);
	
	public Model update(int id, Model aModel);
}
