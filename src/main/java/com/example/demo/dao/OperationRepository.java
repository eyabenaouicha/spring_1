package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.demo.entity.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{

	@Query("select o from Operation o where o.compte.code = ?1")
    public List<Operation> findBycode(Long code);
}
