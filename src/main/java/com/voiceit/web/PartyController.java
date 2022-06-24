package com.voiceit.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voiceit.domain.Party;
import com.voiceit.domain.Role;
import com.voiceit.domain.User;
import com.voiceit.reposiorty.PartyReposiorty;
import com.voiceit.reposiorty.RoleReposiorty;
import com.voiceit.reposiorty.UserRepository;
import com.voiceit.reposiorty.VoteReposiorty;
import com.voiceit.service.PartyService;
import com.voiceit.service.UserService;

@Controller
public class PartyController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PartyService partyService;
	
	
	@GetMapping("/")
	public String showVotingPage(ModelMap model) {
		
		List<Party> parties = partyService.findAll();
//		System.out.println(parties);
		model.put("parties", parties);
		Role role = new Role();
		model.put("role", role);
		return "partiesview";
	}
	
	@GetMapping("/newparty")
	public String createNewParty(ModelMap model) {
		Party nParty = new Party();
		model.put("nParty", nParty);
		
		Role role = new Role();
		model.put("role", role);
		return "newparty";
	}
	
	@PostMapping("/saveparty")
	public String savenNewParty(Party party) {
		partyService.save(party);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editCurrentParty(ModelMap model, @PathVariable Long id) {
		Party party = partyService.findPartyById(id);
		model.put("party", party);
		
		return "editparty";
	}
	
	@PostMapping("/edit/{id}")
	public String saveEditedParty(Party party) {
		partyService.updateParty(party);
		return "redirect:/";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteParty(@PathVariable Long id) {
		partyService.deleteById(id);
		return "redirect:/";
	}
	
//	/*
//	 * Both method work on storing the cookie status 
//	 */
//	@GetMapping("/vote")
//	public String main(Map<String, Object> model, @CookieValue(name = "voted", defaultValue = "false") String voted) {
//	  model.put("voted", Boolean.parseBoolean(voted));
//	  return "vote";
//	}
	
//	@GetMapping("/vote/{id}")
//	public String vote(@PathVariable String id) {
//		String[] ids = id.split("-");
//		Long uid = Long.parseLong(ids[0]);
//		Long pid = Long.parseLong(ids[1]);
//		Optional<User> user = userService.findById(uid);
//		if(user.isPresent()) {
//			User foundUser = user.get();
//			foundUser.setIsVoted(true);
//			userService.update(foundUser);
//			
//		}
//		Party party = partyService.findPartyById(pid);
//		if(party.getId() != null) {
////			Party foundParty = party.get();
////			foundUser.setIsVoted(true);
////			userService.update(foundUser);
//			
////			partyService.updateParty(party);
//			partyService.vote(pid);
//		}
//	  return "redirect:/";
//	}
	
//	@GetMapping("/vote/{uid}/{pid}")
//	public String vote(@PathVariable Long uid, @PathVariable Long pid) {
////		String[] ids = id.split("-");
////		Long uid = Long.parseLong(ids[0]);
////		Long pid = Long.parseLong(ids[1]);
//		Optional<User> user = userService.findById(uid);
//		if(user.isPresent()) {
//			User foundUser = user.get();
//			foundUser.setIsVoted(true);
//			userService.update(foundUser);
//			
//		}
//		Party party = partyService.findPartyById(pid);
//		if(party.getId() != null) {
////			Party foundParty = party.get();
////			foundUser.setIsVoted(true);
////			userService.update(foundUser);
//			
////			partyService.updateParty(party);
//			partyService.vote(pid);
//		}
//	  return "redirect:/";
//	}
	@PostMapping("/vote/{uid}-{pid}")
	public String vote(@PathVariable Long uid, @PathVariable Long pid) throws InterruptedException {
	Optional<User> user = userService.findById(uid);
	if(user.isPresent() && !user.get().isVoted()) {
		PartyThread partyThread = new PartyThread(pid);
		UserThread userThread = new UserThread(uid);
		partyThread.start();
		userThread.start();
		
		// put some comments
		
		partyThread.join();
		userThread.join();
	}
	
	System.out.println("time to return vote");
	return "redirect:/votingresults";
	}
	
	
	
//	@GetMapping("/vote/{id}")
//	public String vote(HttpServletResponse response, @CookieValue(name = "voted", defaultValue = "false") String voted, @PathVariable Long id) {
//	  if (!Boolean.parseBoolean(voted)) {
//	    partyService.vote(id);
//	    Cookie cookie = new Cookie("voted", "true");
//	    response.addCookie(cookie);
//	  }
//
//	  return "redirect:/";
//	}
	
	public static String getUrl(String value) {
		
		return value;
	}
	
	private class PartyThread extends Thread {
		private final Long pid;
		
		public PartyThread(Long pid) {
			this.pid = pid;
		}
		
		@Override
		public void run() {
			System.out.println(" party threading is runing with id: " + pid);
			Party party = partyService.findPartyById(pid);
			if(party.getId() != null) {
				partyService.updateParty(party);
			}
			
		}
	}
	
	private class UserThread extends Thread {
		private final Long uid;
		
		public UserThread(Long uid) {
			this.uid = uid;
		}
		
		@Override
		public void run() {
			System.out.println(" user threading is runing with id: " + uid);
			Optional<User> user = userService.findById(uid);
			if(user.isPresent()) {
				User foundUser = user.get();
				foundUser.setIsVoted(true);
				userService.update(foundUser);
			}
		}
	}
	
	
}
