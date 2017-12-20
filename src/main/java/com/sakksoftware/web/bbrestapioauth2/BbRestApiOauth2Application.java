package com.sakksoftware.web.bbrestapioauth2;

import com.sakksoftware.web.bbrestapioauth2.Service.AccountService;
import com.sakksoftware.web.bbrestapioauth2.model.Account;
import com.sakksoftware.web.bbrestapioauth2.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class BbRestApiOauth2Application {

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(BbRestApiOauth2Application.class, args);
	}
/*
	@Bean
	CommandLineRunner init(AccountService accountService) {
		return (evt) -> Arrays.asList(
				"user,admin,john,robert,ana".split(",")).forEach(
				username -> {
					Account account = new Account();
					account.setUsername(username);
					account.setPassword("password");
					account.setUsername(username);
					account.setFirstname(username);
					account.setLastname("LastName");
					account.grantAuthority(Role.ROLE_USER);
					if (username.equals("admin"))
						account.grantAuthority(Role.ROLE_ADMIN);
					accountService.registerUser(account);
				}
		);
	}

*/

}
