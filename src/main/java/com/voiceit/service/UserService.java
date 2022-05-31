package com.voiceit.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.voiceit.domain.Role;
import com.voiceit.domain.User;
import com.voiceit.reposiorty.RoleReposiorty;
import com.voiceit.reposiorty.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
//	private RoleReposiorty roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    public UserService(UserRepository userRepository,
//    		RoleReposiorty roleRepository,
//                       BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//	
	
	public Boolean isAdmin(Long roleId) {
		User user = userRepository.getById(roleId);
		Set<Role> userRoles = user.getRoles();
		for(Role singleRole: userRoles) {
			if(singleRole.getName().equals("admin")) {
				return true;
			}
		}
		return false;
	}

	// TODO : need to do some checks if the username or email is used
	public User saveNewUser(User user) {
		User nuser = new User();
		nuser.setUsername(user.getUsername());
		nuser.setEmail(user.getEmail());
		
//		nuser.setPassword(user.getPassword());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		nuser.setFirstName(user.getFirstName());
		nuser.setLastName(user.getLastName());
		// TODO: is this how you set the role of the user? 
		nuser.setRoles(new HashSet<Role> (Arrays.asList(new Role("user"))));
		return userRepository.save(nuser);
		
		
	}
	
//	public void saveNewUser(User user) {
//		userRepository.save(user);
//		
//		
//	}
	
//	public User saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
////        user.setActive(true);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        return userRepository.save(user);
//    }


	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	
	}
	
	  public User findUserByUserName(String userName) {
	        return userRepository.findByUsername(userName);
	    }

	public User save(User user) {
		
		user.setRoles(new HashSet<Role> (Arrays.asList(new Role("user"))));
		return userRepository.save(user);
		
	}

}
