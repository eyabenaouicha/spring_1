package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
private final UserRepository userRepository;
@Autowired
public UserService(UserRepository userRepository) {
this.userRepository = userRepository;
}

@Autowired
private  BCryptPasswordEncoder bCryptPasswordEncoder;
/*
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
Objects.requireNonNull(username);
User user = userRepository.findUserWithName(username)
.orElseThrow(() -> new UsernameNotFoundException("User not found"));
return user;
}
*/

public User saveUser(String username,String password,String confirmedpassword) {
User appUser=new User();
if(userRepository.findUserWithName(username).isPresent()==true)throw new RuntimeException("User already exists");
//if(!password.equals(confirmedpassword)) throw new RuntimeException("Please confirm your password");
appUser.setUsername(username);
appUser.setEnabled(true);
//UserDetails user = new User();

Set<Role> roles=new HashSet<Role>();
roles.add(new Role(1,"Admin"));
appUser.setRoles(roles);
appUser.setPassword(bCryptPasswordEncoder.encode(password));
userRepository.save(appUser);
return appUser;

}

//aide a rechercher un utilisateur par nom
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
Objects.requireNonNull(username);
User user = userRepository.findUserWithName(username)
.orElseThrow(() -> new UsernameNotFoundException("User not found"));
Collection<GrantedAuthority> authorities=new ArrayList<>();
user.getRoles().forEach(r->{
 authorities.add(new SimpleGrantedAuthority(r.getName()));
});
return new
org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
}
}