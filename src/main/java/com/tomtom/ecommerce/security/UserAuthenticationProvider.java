package com.tomtom.ecommerce.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class UserAuthenticationProvider extends WebSecurityConfigurerAdapter {

	@Value("${auth.manager.customer.id}")
	private String customerId;

	@Value("${auth.manager.customer.pass}")
	private String customerPass;

	@Value("${auth.manager.seller.id}")
	private String sellerId;

	@Value("${auth.manager.seller.pass}")
	private String sellerPass;

	@Value("${auth.manager.customer.role}")
	private String customerRole;

	@Value("${auth.manager.seller.role}")
	private String sellerRole;

	private static final String CUSTOMER_BASE_URL = "/customer/**";
	private static final String SELLER_BASE_URL = "/seller/**";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(CUSTOMER_BASE_URL).hasRole(customerRole)
		.antMatchers(SELLER_BASE_URL).hasRole(sellerRole)
		.anyRequest().authenticated().and().httpBasic();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		
		UserDetails customerDetails= User.withUsername(customerId)
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password(  customerPass)
				.roles(customerRole).build();
		UserDetails sellerDetails= User.withUsername(sellerId)
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
				.password(  sellerPass)
				.roles(sellerRole).build();
		
		InMemoryUserDetailsManager userManager = new InMemoryUserDetailsManager();
		userManager.createUser(sellerDetails);
		userManager.createUser(customerDetails);
		return userManager;
	}
	

}
