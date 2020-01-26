package com.adesso.demo.automotiveapp.service;

import java.math.BigDecimal;
import java.util.Set;

public interface PricingService {

	public BigDecimal getPrice(int modelId, Set<Integer> set);
}
