package com.voiceit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//@RequestMapping(value = "voiceit-app.herokuapp.com/login")
public class LoginController {
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}