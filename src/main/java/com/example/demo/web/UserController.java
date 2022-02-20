package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
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



import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import lombok.Data;


@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userDetailsService;
	
	/*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
String username = userDetails.getUsername();
	*/
/*	 @GetMapping("/testauth")
	 public String getauthenificated() {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	if (principal instanceof UserDetails) {
		
	   String username = ((UserDetails)principal).getUsername();
	   
	   return username;
	} else {
	  String username = principal.toString();
	  return username;
	}
	 }*/
	 
	
	
	 
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/user")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	
	@PostMapping("/user")
	public User add(@RequestBody User user){
	return userRepository.save(user);
	
	}
	

	 @GetMapping("/user/{id}")
	 Optional<User> oneuser(@PathVariable Integer id) {
	    
	    return userRepository.findById(id);
	     
	  }

	  @PutMapping("/user/{id}")
	  User changeuser(@RequestBody User newUser, @PathVariable Integer id) {
	    
	    return userRepository.findById(id)
	      .map(user -> {
	        user.setUsername(newUser.getUsername());
	        user.setPassword(newUser.getPassword());
	        user.setRoles(newUser.getRoles());
	        
	        
	        return userRepository.save(user);
	      })
	      .orElseGet(() -> {
	        newUser.setUserId(id);
	        return userRepository.save(newUser);
	      });
	  }
	 
	  @DeleteMapping("/user/{id}")
	  void deleteuser(@PathVariable Integer id) {
		  userRepository.deleteById(id);
	  }
	  /*
	  methode qui utilise le userservice pour faire l'authentification
	   * 
	   */
	@Autowired
	  private UserService accountservice;
	  
	  @PostMapping("/register")
		public User register(@RequestBody UserForm userForm){
			  	  return accountservice.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPasssword());
		  }
		  
	}

	class UserForm{
		  private String username;
		  private String password;
		  private String confirmedPasssword;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmedPasssword() {
			return confirmedPasssword;
		}
		public void setConfirmedPasssword(String confirmedPasssword) {
			this.confirmedPasssword = confirmedPasssword;
		}
		public UserForm(String username, String password, String confirmedPasssword) {
			super();
			this.username = username;
			this.password = password;
			this.confirmedPasssword = confirmedPasssword;
		}
		public UserForm() {
			super();
			// TODO Auto-generated constructor stub
		}
		  
		  

		  
	}
