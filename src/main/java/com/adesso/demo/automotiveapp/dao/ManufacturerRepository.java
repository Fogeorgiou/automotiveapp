package com.adesso.demo.automotiveapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adesso.demo.automotiveapp.entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

}
