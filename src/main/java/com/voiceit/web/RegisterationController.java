package com.voiceit.web;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voiceit.domain.Role;
import com.voiceit.domain.User;
import com.voiceit.service.UserService;

@Controller
public class RegisterationController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/register")
	public String getRegisterPage(ModelMap model) {
		User user = new User();
		model.put("user", user);
		return "register";
	}
	
//	@PostMapping("/register")
//	public String postRegister(@RequestBody User user,  BindingResult result) {
//		User existingUser = userService.findUserByUserName(user.getUsername());
//		if(existingUser != null) {
//			result.rejectValue("email", null, "this email is already used");
//		}
//		if (result.hasErrors()) {
//	            return "register";
//	        }
//		
//		userService.saveNewUser(existingUser);
//		return "redirect:/";
//	}
	
	@PostMapping("/register")
	public String postRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    userService.save(user);
		return "redirect:/";
	}
	
	
}
