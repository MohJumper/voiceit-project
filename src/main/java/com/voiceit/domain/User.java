package com.voiceit.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;




@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @NotEmpty
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @NotBlank
    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;
    @NotBlank
    @NotEmpty
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Size(min= 5, max= 18, message = "first name must be at least 18")
    @NotBlank
    @NotEmpty
    @Column(name = "first_name" , nullable = false)
    private String firstName;
    @NotBlank
    @NotEmpty
    @Column(name = "last_name" , nullable = false)
    private String lastName;
    
    
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdDate;
    
    @Column(name = "voted", columnDefinition="tinyint(0) default 0")
    private boolean isVoted;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")
    )
    
    
    
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

	public boolean isVoted() {
		return isVoted;
	}

	public void setVoted(boolean isVoted) {
		this.isVoted = isVoted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", createdDate=" + ", roles=" + roles + "]";
	}
	
    
}