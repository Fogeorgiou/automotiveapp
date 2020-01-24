package com.adesso.demo.automotiveapp.service;

import java.util.List;

import com.adesso.demo.automotiveapp.entity.Feature;

public interface FeatureService {

	public List<Feature> findAll();
	
	public Feature findById(int id);
}
