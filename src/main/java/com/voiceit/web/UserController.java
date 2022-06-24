package com.voiceit.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.voiceit.domain.Role;
import com.voiceit.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/deniedaccess")
	public String showWelcomePage(ModelMap model) {
		
//		List<Party> parties = partyReposiorty.findAll();
//		model.put("parties", parties);
		Role role = new Role();
		model.put("role", role);
		return "deniedaccess";
	}
	
	@GetMapping("/isadmin/{id}")
	public Boolean isAdmin(@PathVariable Long roleId) {
		return userService.isAdmin(roleId);
	}
	

}
