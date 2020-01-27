package com.adesso.demo.automotiveapp.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.adesso.demo.automotiveapp.dao.DbValueConverter;

@Entity
@Table(name="models")
public class Model {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty(message = "Please provide a model name")
	@Column(name="name")
	private String name;
	
	@NotNull(message = "Please provide a base cost for the model")
	@Column(name="base_cost")
	private BigDecimal baseCost;
	
	@NotEmpty(message = "Please provide a manufacturer for the model")
	@Column(name="manufacturer")
	private String manufacturer;
	
	@Column(name="available_features")
	@Convert(converter = DbValueConverter.class)
	private List<String> availableFeatures;
		
	public Model() {
		
	}

	public Model(String name, BigDecimal baseCost, String manufacturer, List<String> availableFeatures) {
		this.name = name;
		this.baseCost = baseCost;
		this.manufacturer = manufacturer;
		this.availableFeatures = availableFeatures;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(BigDecimal baseCost) {
		this.baseCost = baseCost;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public List<String> getAvailableFeatures() {
		return availableFeatures;
	}

	public void setAvailableFeatures(List<String> availableFeatures) {
		this.availableFeatures = availableFeatures;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", name=" + name + ", baseCost=" + baseCost + ", "
				+ "manufacturer=" + manufacturer + "availableFeatures=" + availableFeatures + "]";
	}
}
