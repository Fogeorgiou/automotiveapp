package com.adesso.demo.automotiveapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adesso.demo.automotiveapp.dao.FeatureRepository;
import com.adesso.demo.automotiveapp.entity.Feature;

@Service
public class FeatureServiceImpl implements FeatureService {

	private FeatureRepository featureRepository;
	
	@Autowired
	public FeatureServiceImpl(FeatureRepository aFeatureRepository) {
		featureRepository = aFeatureRepository;
	}
	
	@Override
	public List<Feature> findAll() {
		return featureRepository.findAll();
	}
	
	@Override
	public Feature findById(int id) {
		return featureRepository.findById(id).orElseThrow();
	}
}
