package com.voiceit.web;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.voiceit.domain.Party;
import com.voiceit.domain.Role;
import com.voiceit.domain.User;
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

	/*
	 * Vote method that do two things; turn isVoted to true when clicked 
	 * Increment vote by one. Multi-threading is used for better performance 
	 */
	@PostMapping("/vote/{uid}-{pid}")
	public String vote(@PathVariable Long uid, @PathVariable Long pid) throws InterruptedException {
	Optional<User> user = userService.findById(uid);
	if(user.isPresent() && !user.get().isVoted()) {
		PartyThread partyThread = new PartyThread(pid);
		Thread myPartyThread = new Thread(partyThread);
		UserThread userThread = new UserThread(uid);
		Thread myUserThread = new Thread(userThread);
		myPartyThread.start();
		myUserThread.start();
		
		// if you remove the .join() you will not see the result reflected after the click
		
		myPartyThread.join();
		myUserThread.join();
	}
	
	System.out.println("time to return vote");
	return "redirect:/votingresults";
	}
	
	// -------------------------------------- Helper multi-threading classes -------------------------------- 
		/*
		 * might consider moving out a separate package.
		 */
	private class PartyThread implements Runnable {
		private final Long pid;
		
		public PartyThread(Long pid) {
			this.pid = pid;
		}
		
		@Override
		public void run() {
			System.out.println(" party threading is runing with id: " + pid);
			Party party = partyService.findPartyById(pid);
			if(party.getId() != null) {
				partyService.vote(party);
				
			}
			
		}
	}
	
	private class UserThread implements Runnable {
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
