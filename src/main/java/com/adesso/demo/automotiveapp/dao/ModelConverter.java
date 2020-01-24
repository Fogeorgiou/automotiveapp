package com.adesso.demo.automotiveapp.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.util.StringUtils;

@Converter
public class ModelConverter implements AttributeConverter<List<String>, String> {

	private static final String SEPARATOR = ", ";

	 /**
	  * Convert a String with format "X3, X5, A3 Convertible, A8, Golf"
	  * to a List of Strings that represent models
	  */
	 @Override
	 public List<String> convertToEntityAttribute(String modelsAsString) {
		 if (StringUtils.isEmpty(modelsAsString)) {
			 return Collections.emptyList();
		 }
		 // Step 1: Remove the double quotes from the string
		 modelsAsString = modelsAsString.substring(1, modelsAsString.length() - 2);
		 // Step 2: Split the string into the strings that represent the models
		 String[] models = modelsAsString.split(SEPARATOR);
		 return Arrays.asList(models);
	 }

	/**
	 * Convert a List of Strings that represent models
	 * to a String with format "X3, X5, A3 Convertible, A8, Golf"
	 */
	@Override
	public String convertToDatabaseColumn(List<String> models) {
		StringBuilder sb = new StringBuilder();
		models.forEach(model -> sb.append(model).append(SEPARATOR));
		// Remove the last separator
		String dbString = sb.toString().substring(0, sb.toString().length() - 2);
		return "\"".concat(dbString).concat("\"");
	}
}
