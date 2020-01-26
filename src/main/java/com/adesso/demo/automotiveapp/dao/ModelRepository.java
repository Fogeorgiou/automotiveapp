package com.adesso.demo.automotiveapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adesso.demo.automotiveapp.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {

}
