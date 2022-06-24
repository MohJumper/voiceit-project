package com.voiceit.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.voiceit.domain.Party;
import com.voiceit.domain.Role;
import com.voiceit.domain.User;
import com.voiceit.exception.EmailAlreadyExistException;
import com.voiceit.exception.UsernameAlreadyExisitException;
import com.voiceit.reposiorty.RoleReposiorty;
import com.voiceit.reposiorty.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
//	private RoleReposiorty roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    PasswordEncoder passwordEncoder;

	
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

	/*
	 * JPA handle sorting the object in Mysql so this method is not needed. But kept here for future reference
	 */
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
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public User findUserByUserName(String userName) {
		
	    return userRepository.findByUsername(userName);
	    }
	
	/*
	 * JPA does handle saving the object so this makes a call to the save method and setting all role to a user as a default
	 * This also throw errors if the username of email is used before
	 */
	public User save(User user) throws EmailAlreadyExistException, UsernameAlreadyExisitException {
		if(checkIfUserEmailExist(user.getEmail())) {
			throw new EmailAlreadyExistException("This email already exist. Use different email.");
		}
		if(checkIfUsernameExist(user.getUsername())) {
			throw new UsernameAlreadyExisitException("This username already exist. User different username");
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String encodedPassword = passwordEncoder.encode(user.getPassword());
    	user.setPassword(encodedPassword);
		user.setRoles(new HashSet<Role> (Arrays.asList(new Role("user"))));
		return userRepository.save(user);
		
	}
	
	public void update(User user) {
		Optional<User> currUserOpt = userRepository.findById(user.getId());

		if(currUserOpt.isPresent()) {
			userRepository.save(user);

		}
		
	}
	// ----------------------------------------------------- helper methods for user validation ------------------------------------------- 
	
	 private boolean checkIfUserEmailExist(String email) {
	        return userRepository.findByEmail(email) !=null ? true : false;
	    }
	 private boolean checkIfUsernameExist(String useranme) {
	        return userRepository.findByUsername(useranme) !=null ? true : false;
	    }
	 
	 private void encodePassword( User user, UserData userData){
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	    }

}
