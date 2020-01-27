package com.adesso.demo.automotiveapp.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.adesso.demo.automotiveapp.dao.FeatureRepository;
import com.adesso.demo.automotiveapp.dao.ModelRepository;

@Service
public class PricingServiceImpl implements PricingService {

	private ModelRepository modelRepository;
	private FeatureRepository featureRepository;
	
	@Autowired
	public PricingServiceImpl(ModelRepository aModelRepository, FeatureRepository aFeatureRepository) {
		modelRepository = aModelRepository;
		featureRepository = aFeatureRepository;
	}
	
	@Override
	public BigDecimal getPrice(int modelId, Set<Integer> featureIds) {
		BigDecimal totalCost = BigDecimal.ZERO;
		
		try {
			totalCost = modelRepository.findById(modelId).get().getBaseCost();
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Model Id: " + modelId + " not found", ex);
		}
		
		if (!CollectionUtils.isEmpty(featureIds)) {
			for (Integer featureId : featureIds) {
				try {
					totalCost = totalCost.add(featureRepository.findById(featureId).get().getCost());
				} catch (NoSuchElementException ex) {
					throw new ResponseStatusException(
					          HttpStatus.NOT_FOUND, "Feature Id: " + featureId + " not found", ex);
				}
			}
		}

		return totalCost;
	}

}
