package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.model.MainModel;

public interface MainRepository extends JpaRepository<MainModel,Long>{
    
}
