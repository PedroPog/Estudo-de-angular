package com.api.pix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableTransactionManagement
@EnableWebMvc
@SpringBootApplication
public class ApiPix {

	public static void main(String[] args) {
		System.out.println("senha= "+new BCryptPasswordEncoder().encode("123"));
		SpringApplication.run(ApiPix.class, args);
	}

}
