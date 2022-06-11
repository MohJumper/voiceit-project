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

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//      http.authorizeRequests()
////      .antMatchers("/").permitAll()
//      // ------- add this for register page
////      .authorizeRequests()
//      .antMatchers(
//          "/register",
//          "/js/**",
//          "/css/**",
//          "/img/**",
//          "/webjars/**")
//      
//      .permitAll()
////      .anyRequest()
////      .authenticated()
//      .and()
//      .authorizeRequests()
//    // ----------- end 
//      .antMatchers("/delete/**").hasAuthority("admin")
//      .antMatchers("/edit/**").hasAuthority("admin")
//      .antMatchers("/newparty/**").hasAuthority("admin")
//      // add ant matcher for create new party
//      .anyRequest().authenticated()
//      
//      .and()
//      .formLogin()
//      .loginPage("/login")
//      .permitAll()
//      .and().csrf().disable()
//      .logout().permitAll()
//      .and()
//      // TODO implement error here 
//      .exceptionHandling().accessDeniedPage("/product/error")
//      ;
//    }
    
    /*
     * Working on making the registration work
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
//      .antMatchers("/").permitAll()
      // ------- add this for register page
//      .authorizeRequests()
      .antMatchers(
          "/register",
          "/vote/**",
          "/js/**",
          "/css/**",
          "/img/**",
          "/webjars/**")
      
      .permitAll()
//      .anyRequest()
//      .authenticated()
      .and()
      .authorizeRequests()
    // ----------- end 
      .antMatchers("/delete/**").hasAuthority("admin")
      .antMatchers("/edit/**").hasAuthority("admin")
      .antMatchers("/newparty/**").hasAuthority("admin")
      // add ant matcher for create new party
      .anyRequest().authenticated()
      
      .and()
      .formLogin()
      .loginPage("/login")
      .permitAll()
      .and().csrf().disable()
      .logout().permitAll()
      .and()
      // TODO implement error here 
      .exceptionHandling().accessDeniedPage("/deniedaccess")
      ;
    }

   
}