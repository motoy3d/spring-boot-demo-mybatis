package com.example.actor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated();
//
//		http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
//				.loginProcessingUrl("/login/auth").failureUrl("/login/error").permitAll();
//
//		http.logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
//				.invalidateHttpSession(true);
//
//	}
}