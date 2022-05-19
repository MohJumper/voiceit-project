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
import com.voiceit.reposiorty.PartyReposiorty;
import com.voiceit.reposiorty.RoleReposiorty;
import com.voiceit.reposiorty.UserRepository;
import com.voiceit.reposiorty.VoteReposiorty;
import com.voiceit.service.PartyService;

@Controller
public class PartyController {
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private VoteReposiorty voteReposiorty;
	
	@Autowired
	private RoleReposiorty roleReposiorty;
	
	@Autowired
	private PartyService partyService;
	
	
	@GetMapping("/")
	public String showWelcomePage(ModelMap model) {
		
		List<Party> parties = partyService.findAll();
//		System.out.println(parties);
		model.put("parties", parties);
		return "partiesview";
	}
	
	@GetMapping("/newparty")
	public String createNewParty(ModelMap model) {
		Party nParty = new Party();
		model.put("nParty", nParty);
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
		this.partyService.deleteById(id);
		return "redirect:/";
	}
}
