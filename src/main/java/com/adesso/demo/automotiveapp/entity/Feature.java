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

import com.adesso.demo.automotiveapp.dao.ModelConverter;

@Entity
@Table(name="features")
public class Feature {

	// fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@NotEmpty(message = "Please provide a feature name")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message = "Please provide a cost for the feature")
	@Column(name="cost")
	private BigDecimal cost;
	
	@Column(name="models")
	@Convert(converter = ModelConverter.class)
	private List<String> models;
		
	// constructors
	
	public Feature() {
		
	}

	public Feature(String name, BigDecimal cost, List<String> models) {
		this.name = name;
		this.cost = cost;
		this.models = models;
	}

	// getter/setter methods
	
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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}

	// toString

	@Override
	public String toString() {
		return "Feature [id=" + id + ", name=" + name + ", cost=" + cost + ", models=" + models + "]";
	}
}
