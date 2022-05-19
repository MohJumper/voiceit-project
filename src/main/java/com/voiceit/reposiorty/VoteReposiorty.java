package com.voiceit.reposiorty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voiceit.domain.Vote;

public interface VoteReposiorty extends JpaRepository<Vote, Long> {

}
