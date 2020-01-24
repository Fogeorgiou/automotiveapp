package com.adesso.demo.automotiveapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adesso.demo.automotiveapp.entity.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {

}
