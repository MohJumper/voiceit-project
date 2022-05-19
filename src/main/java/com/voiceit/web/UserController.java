package com.voiceit.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.voiceit.domain.Party;
import com.voiceit.reposiorty.PartyReposiorty;
import com.voiceit.reposiorty.RoleReposiorty;
import com.voiceit.reposiorty.UserRepository;
import com.voiceit.reposiorty.VoteReposiorty;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PartyReposiorty partyReposiorty;
	
	@Autowired
	private VoteReposiorty voteReposiorty;
	
	@Autowired
	private RoleReposiorty roleReposiorty;
	
	
	@GetMapping("/welcome")
	public String showWelcomePage(ModelMap model) {
		
//		List<Party> parties = partyReposiorty.findAll();
//		model.put("parties", parties);
		return "welcome";
	}
	
	

}
