package com.voiceit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voiceit.domain.Party;
import com.voiceit.reposiorty.PartyReposiorty;

@Service
public class PartyService {
	
	@Autowired
	private PartyReposiorty partyReposiorty;

	public List<Party> findAll() {
		
		return partyReposiorty.findAll();
	}

	public void save(Party party) {
		
		partyReposiorty.save(party);
	}

	public Party findPartyById(Long id) {
		Optional<Party> partyOpt = partyReposiorty.findById(id);
        if(partyOpt.isPresent()) {
        	return partyOpt.get();
        } else {
        	throw new RuntimeException(" sale not found for id: " + id);
        }
	}

	public Party updateParty(Party party) {
		Optional<Party> currPartyOpt = partyReposiorty.findById(party.getId());

		if(currPartyOpt.isPresent()) {
			Party nParty = currPartyOpt.get();
			nParty.setId(party.getId());
			nParty.setName(party.getName());
			
			nParty = partyReposiorty.save(nParty);
			
			return nParty;
			
		} else {
			party = partyReposiorty.save(party);
			 return party;
		}
		
	}

	public void deleteById(Long id) {
		partyReposiorty.deleteById(id);
		
	}

	public void vote(Long id) {
		Optional<Party> currParty = partyReposiorty.findById(id);
		if(currParty.isPresent()) {
			Party nParty = currParty.get();
			nParty.setVoteCount(nParty.getVoteCount() +1);
			updateParty(nParty);
		}
		
	}
	
	

}
