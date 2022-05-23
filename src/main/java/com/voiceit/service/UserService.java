package com.voiceit.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voiceit.domain.Role;
import com.voiceit.domain.User;
import com.voiceit.reposiorty.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
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

}
