package com.voiceit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.voiceit.domain.User;
import com.voiceit.reposiorty.UserRepository;


public class UserDetailServiceImpl implements UserDetailsService {

	  @Autowired
	  private UserRepository userRepository;

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user= userRepository.getUserByUsername(username);
	        
	        if(user==null){
	            throw new UsernameNotFoundException("username not found");
	        }
			return new MyUserDetail(user);
		}
	    
}
