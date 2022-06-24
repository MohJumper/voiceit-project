package com.voiceit.reposiorty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.voiceit.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.username= :username")
	public User getUserByUsername(@Param("username") String username);
	
	//@Query("select u from User u where u.email= :email")
	public User findByEmail(@Param("email")String email);

	public User findByUsername(String username);
	
//	@Query("select u from User u where u.id= :id")
	public Optional<User> findById(@Param("id") Long id);

}
