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

@Controller
public class PartyController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PartyReposiorty partyReposiorty;
	
	@Autowired
	private VoteReposiorty voteReposiorty;
	
	@Autowired
	private RoleReposiorty roleReposiorty;
	
	@GetMapping("/")
	public String showWelcomePage(ModelMap model) {
		
		List<Party> parties = partyReposiorty.findAll();
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
		partyReposiorty.save(party);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editCurrentParty(ModelMap model, @PathVariable Long id) {
		Optional<Party> currParty = partyReposiorty.findById(id);
		model.put("currParty", currParty);
		
		return "editparty";
	}
	
	@PostMapping("/edit/{id}")
	public String saveEditedParty(Party party) {
		Optional<Party> currPartyOpt = partyReposiorty.findById(party.getId());
//		 if(currPartyOpt.isPresent()) {
//	        	currPartyOpt.get();
//	        } else {
//	        	throw new RuntimeException(" sale not found for id: " + party.getId());
//	        }
		if(currPartyOpt.isPresent()) {
			Party nParty = currPartyOpt.get();
			nParty.setId(party.getId());
			nParty.setName(party.getName());
			
			partyReposiorty.save(nParty);
//			 return nparty;
			
//		} else {
//			partyReposiorty.save(party);
			// return party;
		}
		return "redirect:/";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteParty(@PathVariable Long id) {
		this.partyReposiorty.deleteById(id);
		return "redirect:/";
	}
}
