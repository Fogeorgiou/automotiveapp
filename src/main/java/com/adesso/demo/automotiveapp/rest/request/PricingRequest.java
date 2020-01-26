package com.adesso.demo.automotiveapp.rest.request;

import java.util.Set;

public class PricingRequest {

	private int modelId;
	
	private Set<Integer> featureIds;
	
	public PricingRequest(int aModelId, Set<Integer> someFeatureIds) {
		modelId = aModelId;
		featureIds = someFeatureIds;
	}
	
	public int getModelId() {
		return modelId;
	}
	
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	
	public Set<Integer> getFeatureIds() {
		return featureIds;
	}
	
	public void setFeatureIds(Set<Integer> featureIds) {
		this.featureIds = featureIds;
	}
	
	@Override
	public String toString() {
		return "PricingRequest [modelId=" + modelId + ", featureIds=" + featureIds + "]";
	}
}
