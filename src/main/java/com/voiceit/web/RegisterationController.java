package com.voiceit.web;

import java.util.Arrays;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.voiceit.domain.Role;
import com.voiceit.domain.User;
import com.voiceit.exception.EmailAlreadyExistException;
import com.voiceit.exception.UsernameAlreadyExisitException;
import com.voiceit.service.UserData;
import com.voiceit.service.UserService;

@Controller
public class RegisterationController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/register")
	public String getRegisterPage(ModelMap model) {
//		User user = new User();
		UserData user = new UserData();
		model.put("userData", user);
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(final @Valid @ModelAttribute("userData") UserData userData, final BindingResult bindingResult, final Model model) {
		if(bindingResult.hasErrors()) {
//			model.addAttribute("userData", userData);
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
			model.addAttribute("userData", userData);
//			e.printStackTrace();
			return "register";
		}
		return "redirect:/";
	}
	
	// --------------------------------------------------------------- 
	
//	@PostMapping("/register")
//	public String postRegister(User user) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		User userExists = userService.findUserByUserName(user.getUsername());
//
//	    String encodedPassword = passwordEncoder.encode(user.getPassword());
//	    user.setPassword(encodedPassword);
//	    
//	    try {
//			userService.save(user);
//		} catch (EmailAlreadyExistException | UsernameAlreadyExisitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "redirect:/";
//	}
	// --------------------------------------------------------------------- 
//	@PostMapping("/register")
//	public ModelAndView postRegister(@Validated User user, BindingResult bindingResult) {
//		
//        ModelAndView modelAndView = new ModelAndView();
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		User userExists = userService.findUserByUserName(user.getUsername());
//		if(userExists != null) {
//			bindingResult.rejectValue("userName", 
//									 "error.user", 
//									 "This username is alraedy taken");
//		}
//		if (bindingResult.hasErrors()) {
//	            modelAndView.setViewName("register");
//			} else {
//				 try {
//						userService.save(user);
//					} catch (EmailAlreadyExistException | UsernameAlreadyExisitException e) {
//						e.printStackTrace();
//					}
//				 String encodedPassword = passwordEncoder.encode(user.getPassword());
//				 user.setPassword(encodedPassword);
//				 modelAndView.addObject("successMessage", "User has been registered successfully");
//				 modelAndView.addObject("user", new User());
//				 modelAndView.setViewName("register");
//			}
//		
//	   
//	    
////	    try {
////			userService.save(user);
////		} catch (EmailAlreadyExistException | UsernameAlreadyExisitException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		return "redirect:/";
//		return modelAndView;
//	}

	
	
}
