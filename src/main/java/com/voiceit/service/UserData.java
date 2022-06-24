package com.voiceit.service;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.voiceit.domain.Role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserData implements Serializable{
	@Size(min= 5, max= 18, message = "first name must be at least 18")
	@NotBlank
	@NotEmpty()
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    private String lastName;

    @NotEmpty(message = "Email can not be empty")
//    @Email(message = "Please provide a valid email id")
    private String email;
    
    @NotEmpty(message = "username can not be empty")
    private String username;

    @NotEmpty(message = "Password can not be empty")
    private String password;
    
    private Set<Role> roles=new HashSet<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
    
    

}
