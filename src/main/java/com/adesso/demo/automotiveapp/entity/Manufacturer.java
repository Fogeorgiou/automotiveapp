package com.adesso.demo.automotiveapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.adesso.demo.automotiveapp.dao.DbValueConverter;

@Entity
@Table(name="manufacturers")
public class Manufacturer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty(message = "Please provide a manufacturer name")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message = "Please provide a city for the manufacturer")
	@Column(name="city")
	private String city;
	
	@Column(name="models")
	@Convert(converter = DbValueConverter.class)
	private List<String> models;
		
	public Manufacturer() {
		
	}

	public Manufacturer(String name, String city, List<String> models) {
		this.name = name;
		this.city = city;
		this.models = models;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}

	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", name=" + name + ", city=" + city + ", models=" + models + "]";
	}
}
