package com.voiceit.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.voiceit.service.PartyService;

@Controller
public class ResultsController {
	
	
	@Autowired
	private PartyService partyService;
	
	
	@GetMapping("/votingresults")
	public String getAllCandid(ModelMap model) {
		List<String> candidName = partyService
								.findAll()
								.stream()
								.map(n -> n.getName())
								.collect(Collectors.toList());
		
		List<Integer> candidVotes = partyService
									.findAll()
									.stream()
									.map(v -> v.getVoteCount())
									.collect(Collectors.toList());
		// the name of in the >> "" << is what you will added to your html
		System.out.println(candidName);
		System.out.println(candidVotes);
		model.put("candidName", candidName);
		model.put("candidVotes", candidVotes);
		// your html file name 
		return "displayresultschart";
	}

}
