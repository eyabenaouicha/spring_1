package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Compte;
public interface CompteRepository extends JpaRepository<Compte, Long>{

	//Compte save(int i, int j, Date date);
	
}
