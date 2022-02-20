package com.example.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Client;
import com.example.demo.entity.Compte;
import com.example.demo.entity.Operation;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
@Autowired
UserRepository userRepository;
@Autowired
CompteRepository compteRepository;
@Autowired
RoleRepository roleRepository;
@Autowired
ClientRepository clientRepository;
@Autowired
OperationRepository operationRepository;

@Autowired
UserService userService;
	public static void main(String[] args)  {
		SpringApplication.run(SpringSecurityApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//User u1 = userRepository.save(new User("molka","molka"));
		Role role1=roleRepository.save(new Role(1,"Admin"));
		Role role2=roleRepository.save(new Role(2,"user"));
		Set<Role> roles=new HashSet<Role>();
		roles.add(role1);
		Compte comp=compteRepository.save(new Compte(1,1000000,new Date()));
		Compte comp2=compteRepository.save(new Compte(2,2000000,new Date()));
		
	/*	Client Clien=clientRepository.save(new Client("molka"));
		Operation op=operationRepository.save(new Operation(new Date(),500,1,comp2));
		Operation op2=operationRepository.save(new Operation(new Date(),500,1,comp));*/
		//User u1 = userRepository.save(new User(95,"molka","molkaaa",true,roles));
		User Admin=userService.saveUser("Molka25","Molka25","Molka25");
		Client Clien=clientRepository.save(new Client(2,"Molka25", "Molka25",Admin));
		Compte comp3=compteRepository.save(new Compte(3,3000000,new Date(),Clien));
	}

}
