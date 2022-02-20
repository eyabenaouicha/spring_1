package com.example.demo.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.entity.Compte;
import com.example.demo.entity.Operation;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class CompteController {
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	OperationRepository operationRepository;
	
	
	
	
	@GetMapping("/operation")
	public List<Operation> getOperations(){
		return operationRepository.findAll();
	}
	 @GetMapping("/operation/{code}")
	 Optional<Operation> oneOperation(@PathVariable long code) {
	    
	    return operationRepository.findById(code);
	     
	  }
	@GetMapping("/compte")
	public List<Compte> getComptes(){
		return compteRepository.findAll();
	}
	
	@GetMapping("/compte/operations/{code}")
	  public List<Operation> findBycode(@PathVariable long code){
	
			return operationRepository.findBycode(code);
		
	}
	
	
	@PostMapping("/compte/verser/{code}/{solde}")
	public Operation verser(@PathVariable long code,@PathVariable double solde){
		Optional<Compte> compt=Optional.of(compteRepository.findById(code)).orElseThrow(RuntimeException::new);
		Compte cmpt=compt.get();
		cmpt.setSolde(cmpt.getSolde()+solde);
		 compteRepository.save(cmpt);
	Operation op=new Operation();
	op.setCompte(cmpt);
	op.setDateOperation(new Date());
	op.setMontant(solde);
	op.setType(op.VERSEMENT);
	return operationRepository.save(op);
		 
	}
	
	@PostMapping("/compte/retrait/{code}/{solde}")
	public Operation retrait(@PathVariable long code,@PathVariable double solde ) {
		Optional<Compte> comp=Optional.of(compteRepository.findById(code)).orElseThrow(RuntimeException::new);
		Compte compte=comp.get();
		double resultat=compte.getSolde()-solde;
		if(resultat>=0) {
		compte.setSolde(compte.getSolde()-solde);}
		else {
			compte.setSolde(compte.getSolde());	
		}
	compteRepository.save(compte);
	Operation op=new Operation();
	op.setCompte(compte);
	op.setDateOperation(new Date());
	op.setMontant(solde);
	op.setType(op.RETRAIT);

	return operationRepository.save(op);}
	
	@Autowired
	UserService userDetailsService;
	
	/*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
String username = userDetails.getUsername();
	*/
	 @GetMapping("/testauth")
	
	
	 public String getauthenificated() {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
		//	if (principal instanceof UserDetails) {
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        //UserDetails userDetail = (UserDetails) auth.getPrincipal();
			 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 String currentPrincipalName = authentication.getName();
		        
			  // String username = ((UserDetails)principal).getUsername();
			//Authentication authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 UserDetails user = (UserDetails) service.loadUserByUsername(currentPrincipalName);
			
			   return user.getUsername();
			/*} else {
			  String username = principal.toString();
			  return username;
			}*/
			 }
	
	
/*	@PreAuthorize("#name == authentication.name")
	@PostMapping("compte/Virement/{name}/{code}/{code2}/{solde}")
		public Operation transfer(@PathVariable String name,@PathVariable long code,@PathVariable long code2,@PathVariable double solde) {
		
	
		Optional<Compte> compt=Optional.of(compteRepository.findById(code)).orElseThrow(RuntimeException::new);
		
		Optional<Compte> compt2=Optional.of(compteRepository.findById(code2)).orElseThrow(RuntimeException::new);
		Compte comp1=compt.get();
		Compte comp2=compt2.get();
		double resultat=comp1.getSolde()-solde;
		if(resultat>=0) {
			comp1.setSolde(comp1.getSolde()-solde);
			comp2.setSolde(comp2.getSolde()+solde);}
			else {
				comp1.setSolde(comp1.getSolde());	
			}
		//comp1.setSolde(comp1.getSolde()-solde);
		
		compteRepository.save(comp1);
		compteRepository.save(comp2);
		Operation op=new Operation();
		op.setCompte(comp1);
		op.setDateOperation(new Date());
		op.setMontant(solde);
		op.setType(op.VIREMENT);
		
		return operationRepository.save(op);
		
	}*/
	  @Autowired
	    private UserService service;
	 
	@PreAuthorize("#name == authentication.name")
	@PostMapping("compte/Virement/{name}/{code1}/{code2}/{solde}")
		public Operation transfer(@PathVariable String name,@PathVariable long code1,@PathVariable long code2,@PathVariable double solde) {
		
	
		Optional<Compte> compt=Optional.of(compteRepository.findById(code1)).orElseThrow(RuntimeException::new);
		
		Optional<Compte> compt2=Optional.of(compteRepository.findById(code2)).orElseThrow(RuntimeException::new);
		Compte comp1=compt.get();
		Compte comp2=compt2.get();
		
	//	if(comp1.getClient_id().getId()==code1) {
		double resultat=comp1.getSolde()-solde;
		if(resultat>=0) {
			comp1.setSolde(comp1.getSolde()-solde);
			comp2.setSolde(comp2.getSolde()+solde);}
			else {
				comp1.setSolde(comp1.getSolde());	
			}
		//comp1.setSolde(comp1.getSolde()-solde);
		
		compteRepository.save(comp1);
		compteRepository.save(comp2);
		Operation op=new Operation();
		op.setCompte(comp1);
		op.setDateOperation(new Date());
		op.setMontant(solde);
		op.setType(op.VIREMENT);
		
		return operationRepository.save(op);
		/*}
		else {return null;}*/
	}
	
	@PostMapping("/compte")
	public Compte add(@RequestBody Compte compte){
	return compteRepository.save(compte);
	
	}
	
	 @GetMapping("/compte/{code}")
	 Optional<Compte> oneCompte(@PathVariable long code) {
	    
	    return compteRepository.findById(code);
	     
	  }

	  @PutMapping("/compte/{code}")
	  Compte changeCompte(@RequestBody Compte newCompte, @PathVariable long code) {
	    
	    return compteRepository.findById(code)
	      .map(compte -> {
	        compte.setDatecreation(newCompte.getDatecreation());
	        compte.setSolde(newCompte.getSolde());
	        return compteRepository.save(compte);
	      })
	      .orElseGet(() -> {
	        newCompte.setCode(code);
	        return compteRepository.save(newCompte);
	      });
	  }
	 
	  @DeleteMapping("/compte/{code}")
	  void deleteCompte(@PathVariable long code) {
		  compteRepository.deleteById(code);
	  }
}