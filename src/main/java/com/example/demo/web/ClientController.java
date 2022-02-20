package com.example.demo.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ClientRepository;
import com.example.demo.entity.Client;
import com.example.demo.service.UserService;


@RestController
public class ClientController {
	@Autowired
	ClientRepository clientRepository;
	
	

	@Autowired
	UserService userDetailsService;
	
	/*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
String username = userDetails.getUsername();
	*/
	@GetMapping("/test")
	 public String getauthenificated() {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String id=UUID.randomUUID().toString();
	if (principal instanceof UserDetails) {
		
	   String username = ((UserDetails)principal).getUsername();
	   
	   return id;
	} else {
	  String username = principal.toString();
	  return id;
	}
	 }
	

	@GetMapping("/client")
	public List<Client> getComptes(){
		return clientRepository.findAll();
	}
	
	
	
	@PostMapping("/client")
	public Client add(@RequestBody Client client){
	return clientRepository.save(client);
	
	}
	
	
	@PreAuthorize("#name == authentication.name")
	 @GetMapping("/client/{name}/{id}")
	 Optional<Client> oneClient(@PathVariable String name,@PathVariable Integer id) {
		 //Optional<Client> Clien=clientRepository.findById(id);
		 
		 //if(Clien.get().getName().equals((String)getauthenificated())) {
	    return 	clientRepository.findById(id);
			// return "true"+ Clien.get().getName();
			// }
		 //else {return null;}
	     
	  }

	  @PutMapping("/client/{id}")
	  Client changeClient(@RequestBody Client newClient, @PathVariable Integer id) {
	    
	    return clientRepository.findById(id)
	      .map(client -> {
	        client.setName(newClient.getName());
	        client.setPaasword(newClient.getPaasword());
	        return clientRepository.save(client);
	      })
	      .orElseGet(() -> {
	        newClient.setId(id);
	        return clientRepository.save(newClient);
	      });
	  }
	 
	  @DeleteMapping("/client/{id}")
	  void deleteClient(@PathVariable Integer id) {
		  clientRepository.deleteById(id);
	  }
}
