package com.adesso.demo.automotiveapp.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Converter
public class DbValueConverter implements AttributeConverter<List<String>, String> {

	private static final String SEPARATOR = ", ";

	 /**
	  * Convert a String with format "A, B, C"
	  * to a List of Strings that represent models or features
	  */
	 @Override
	 public List<String> convertToEntityAttribute(String dbValueAsString) {
		 if (StringUtils.isEmpty(dbValueAsString) || dbValueAsString.length() == 1) {
			 return Collections.emptyList();
		 }
		 // Step 1: Remove the double quotes from the string
		 dbValueAsString = dbValueAsString.substring(1, dbValueAsString.length() - 2);
		 // Step 2: Split the string into the strings that represent the models/features
		 String[] values = dbValueAsString.split(SEPARATOR);
		 return Arrays.asList(values);
	 }

	/**
	 * Convert a List of Strings that represent models or features
	 * to a String with format "A, B, C"
	 */
	@Override
	public String convertToDatabaseColumn(List<String> values) {
		StringBuilder sb = new StringBuilder();
		if (CollectionUtils.isEmpty(values)) {
			 return sb.append("\r").toString();
		}
		values.forEach(value -> sb.append(value).append(SEPARATOR));
		// Remove the last separator
		String dbValueAsString = sb.toString().substring(0, sb.toString().length() - 2);
		return "\"".concat(dbValueAsString).concat("\"");
	}
}
