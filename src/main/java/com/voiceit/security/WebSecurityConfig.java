package com.voiceit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Bean
    protected UserDetailsService userDetailsService() {
       
        return new UserDetailServiceImpl();
       
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
        authprovider.setPasswordEncoder(passwordEncoder());
        authprovider.setUserDetailsService(userDetailsService());

        return authprovider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    /*
     * config the authentication and authorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
      .antMatchers(
          "/register",
          "https://voiceit-app.herokuapp.com/login",
          "/login",
          "/vote/**",
          "/js/**",
          "/css/**",
          "/img/**",
          "/webjars/**")
      
      .permitAll()
      .and()
      .authorizeRequests()
    // ----------- end 
      .antMatchers("/delete/**").hasAuthority("admin")
      .antMatchers("/edit/**").hasAuthority("admin")
      .antMatchers("/newparty/**").hasAuthority("admin")
      .anyRequest().authenticated()
      
      .and()
      .formLogin()
      .loginPage("/login")
      .permitAll()
      .and().csrf().disable()
      .logout().permitAll()
      .and()
      // handle error when authorization is denied
      .exceptionHandling().accessDeniedPage("/deniedaccess")
      ;
    }

   
}