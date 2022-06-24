package com.voiceit.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.voiceit.domain.User;
import com.voiceit.exception.EmailAlreadyExistException;
import com.voiceit.exception.UsernameAlreadyExisitException;
import com.voiceit.service.UserService;

@Controller
public class RegisterationController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/register")
	public String getRegisterPage(ModelMap model) {
		User user = new User();
		model.put("userData", user);
		return "register";
	}

	
	@PostMapping("/register")
	public String postRegister(final @Valid @ModelAttribute("userData") User userData, BindingResult bindingResult, final ModelMap model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("userData", userData);
			return "register";
		}
	    
	    try {
	    	
			userService.save(userData);
		} catch (EmailAlreadyExistException | UsernameAlreadyExisitException e) {
			bindingResult.rejectValue("email", 
					 				  "userData.email", 
					 "This email is alraedy taken"
					 				  );
			bindingResult.rejectValue("username", 
	 				  "userData.username", 
	 "This username is alraedy taken");
			bindingResult.rejectValue("firstName", 
	 				  "userData.firstName", 
	 "This firstName can not bee empty");
			bindingResult.rejectValue("lastName", 
	 				  "userData.lastName", 
	 "This lastName can not bee empty");
			bindingResult.rejectValue("password", 
	 				  "userData.password", 
	 "This password can not bee empty");
			
			model.addAttribute("userData", userData);
//			e.printStackTrace();
			return "register";
		}
		return "redirect:/";
	}
	
	// --------------------------------------------------------------- 

	
	
}
