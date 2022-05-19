package com.voiceit.reposiorty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voiceit.domain.Party;

public interface PartyReposiorty extends JpaRepository<Party, Long> {
	
	

}
