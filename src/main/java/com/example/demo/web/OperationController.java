package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.OperationRepository;
import com.example.demo.entity.Compte;
import com.example.demo.entity.Operation;
import com.example.demo.entity.User;
@RestController
public class OperationController {
	@Autowired 
	OperationRepository operationRepository;
	
	
	
	
	
	
	
/*	@PutMapping("/compte/{code}/retrait/{solde}")
	  Compte retraitCompte(@RequestBody Compte newCompte, @PathVariable long code,@PathVariable double solde) {
	    
	    return operationRepository.findById(code)
	      .map(compte -> {
	        compte.setSolde(newCompte.getSolde()-solde);
	        return operationRepository.save(compte);
	      })
	      .orElseGet(() -> {
	        newCompte.setCode(code);
	        return operationRepository.save(newCompte);
	      });
	  }
	
	@PutMapping("/compte/{code}/versement/{solde}")
	  Compte versementCompte(@RequestBody Compte newCompte, @PathVariable long code,@PathVariable double solde) {
	    
	    return operationRepository.findById(code)
	      .map(compte -> {
	        compte.setSolde(newCompte.getSolde()+solde);
	        return operationRepository.save(compte);
	      })
	      .orElseGet(() -> {
	        newCompte.setCode(code);
	        return operationRepository.save(newCompte);
	      });
	  }

	

	@PutMapping("/compte/{code}/transfert/{code2}/transfert/{solde}")
	  Compte transfertCompte(@RequestBody Compte[] Compte1, @PathVariable long code,@PathVariable long code2,@PathVariable double solde) {
	    
	  Compte comp= operationRepository.findById(code)
	      .map(compte -> {
	        compte.setSolde(Compte1[0].getSolde()-solde);
	      return  operationRepository.save(compte);
	      })
	      .orElseGet(() -> {
	    	  Compte1[0].setCode(code);
	        return operationRepository.save(Compte1[0]);
	      });
	  
		  return operationRepository.findById(code2)
			      .map(compte -> {
			        compte.setSolde(Compte1[1].getSolde()+solde);
			        return operationRepository.save(compte);
			      })
			      .orElseGet(() -> {
			    	  Compte1[1].setCode(code);
			        return operationRepository.save(Compte1[1]);
			      });
	  }


	 @GetMapping("/solde/{code}")
	 double oneCompte(@PathVariable long code) {
	    
	    return operationRepository.findById(code).get().getSolde();
	     
	  }
	
	
	*/
}
	
	
	
	
	
	
	
	
	
	

